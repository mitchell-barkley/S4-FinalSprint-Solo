package com.finalsprint.binarytree.controller;

import com.finalsprint.binarytree.model.BinaryEntity;
import com.finalsprint.binarytree.model.BinarySearchTree;
import com.finalsprint.binarytree.services.BinaryTreeService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class BinaryController {
    @Autowired
    private BinaryTreeService service;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/enter_numbers")
    public String enterNumbers() {
        return "enter_numbers";
    }

    @PostMapping("/process")
    public String processInput(@RequestParam("numbers") int[] numbers, Model model) {
        BinarySearchTree tree = BinaryTreeService.processNumbers(numbers);
        model.addAttribute("tree", tree);
        return "result";
    }

    @GetMapping("/prev_trees")
    public String prevTrees(Model model) {
        List<BinaryEntity> entities = service.showPreviousTrees();
        model.addAttribute("entities", entities);
        return "prev_trees";
    }
}