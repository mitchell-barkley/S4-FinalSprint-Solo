package com.finalsprint.binarytree.controller;

import com.finalsprint.binarytree.model.BinaryEntity;
import com.finalsprint.binarytree.model.BinarySearchTree;
import com.finalsprint.binarytree.repository.Repo;
import com.finalsprint.binarytree.services.BinaryTreeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping
public class BinaryController {

    @Autowired
    private BinaryTreeService service;

    @Autowired
    private Repo repo;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String inputNumbers) {
        service.processInput(inputNumbers);
        return "redirect:/";
    }

    @PostMapping("/process")
    public String process(@RequestParam String inputNumbers, Model model) {
        if (inputNumbers == null || inputNumbers.trim().isEmpty()) {
            model.addAttribute("error", "Please enter some numbers");
            return "result";
        }
        try {
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
            model.addAttribute("treeJson", tree.serializeToString());
            return "result";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Invalid input");
            return "result";
        }
    }

    @GetMapping("/trees")
    @ResponseBody
    public List<BinaryEntity> getAllTrees() {
        try {
            return repo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching trees");
        }
    }

    @GetMapping("/tree/{id}")
    public String getTree(@PathVariable Long id, Model model) {
        BinaryEntity tree = service.getTree(id);
        model.addAttribute("tree", tree);
        return "tree";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}