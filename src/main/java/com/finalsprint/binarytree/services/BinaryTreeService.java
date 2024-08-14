package com.finalsprint.binarytree.services;

import com.finalsprint.binarytree.repository.Repo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.finalsprint.binarytree.model.BinarySearchTree;
import com.finalsprint.binarytree.model.BinaryEntity;

import java.util.List;
import java.util.stream.Stream;

@Service
public class BinaryTreeService {

    @Autowired
    private Repo repo;

    public BinarySearchTree processInput(String inputNumbers) {
        int[] numbers = Stream.of(inputNumbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        BinarySearchTree tree = new BinarySearchTree();
        for (int number : numbers) {
            tree.insert(number);
        }
        BinaryEntity entity = new BinaryEntity();
        entity.setTree(tree);
        repo.save(entity);
        return tree;
    }

    public List<BinaryEntity> getAllTrees() {
        return repo.findAll();
    }

    public BinaryEntity getTree(Long id) {
        return repo.findById(id).get();
    }
}