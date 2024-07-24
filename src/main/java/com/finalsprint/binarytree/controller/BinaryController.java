package com.finalsprint.binarytree.controller;

import com.finalsprint.binarytree.model.BinaryEntity;
import com.finalsprint.binarytree.model.BinarySearchTree;
import com.finalsprint.binarytree.Repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BinaryController {

    @Autowired
    private Repo Repository;

    @GetMapping("/")
    public String home() {
        return "redirect:/enter_numbers";
    }

    @GetMapping("/enter_numbers")
    public String enterNumbers() {
        return "enter_numbers.html";
    }

    @PostMapping("/process_numbers")
    public String processNumbers(@RequestParam("number") String numbers, Model model) {
        String[] numberArray = numbers.split(",");
        BinarySearchTree tree = new BinarySearchTree();
        for (String number : numberArray) {
            tree.insert(Integer.parseInt(number));
        }
        tree.balance();
        String treeJson = tree.toJson();
        BinaryEntity entity = new BinaryEntity();
        entity.setInputNumbers(numbers);
        entity.setTreeJson(treeJson);
        Repository.save(entity);
        model.addAttribute("treeJson", treeJson);
        return "result.html";
    }

    @GetMapping("/previous_trees")
    public String showPreviousTrees(Model model) {
        List<BinaryEntity> trees = Repository.findAll();
        model.addAttribute("trees", trees);
        return "previous_trees.html";
    }
}
