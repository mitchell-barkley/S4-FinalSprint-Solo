package com.finalsprint.binarytree.services;

import com.finalsprint.binarytree.repository.Repo;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.finalsprint.binarytree.model.BinarySearchTree;
import com.finalsprint.binarytree.model.BinaryEntity;
import java.util.List;

@Service
public class BinaryTreeService {

    @PostConstruct
    public void init() {
        System.out.println("Binary Tree Service Initialized");
    }

    private final Repo Repository;

    @Autowired
    public BinaryTreeService(Repo Repository) {
        this.Repository = Repository;
    }

    public static BinarySearchTree processNumbers(int[] numbers) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int number : numbers) {
            tree.insert(number);
        }
        return tree;
    }

    public List<BinaryEntity> showPreviousTrees() {
        return Repository.findAll();
    }

    public void showTree(Long id) {
        Repository.findById(id);
    }

    public void saveTree(BinaryEntity entity) {
        Repository.save(entity);
    }

    public static String prev_trees() {
        return "prev_trees";
    }
}
