package com.finalsprint.binarytree.model;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (value < root.getValue()) {
            root.setLeft(insertRec(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insertRec(root.getRight(), value));
        }

        return root;
    }

    public List<Integer> inOrder() {
        List<Integer> list = new ArrayList<>();
        inOrderRec(root, list);
        return list;
    }

    private void inOrderRec(Node root, List<Integer> list) {
        if (root != null) {
            inOrderRec(root.getLeft(), list);
            list.add(root.getValue());
            inOrderRec(root.getRight(), list);
        }
    }

    public List<Integer> preOrder() {
        List<Integer> list = new ArrayList<>();
        preOrderRec(root, list);
        return list;
    }

    private void preOrderRec(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.getValue());
            preOrderRec(root.getLeft(), list);
            preOrderRec(root.getRight(), list);
        }
    }

    public List<Integer> postOrder() {
        List<Integer> list = new ArrayList<>();
        postOrderRec(root, list);
        return list;
    }

    private void postOrderRec(Node root, List<Integer> list) {
        if (root != null) {
            postOrderRec(root.getLeft(), list);
            postOrderRec(root.getRight(), list);
            list.add(root.getValue());
        }
    }

    public Node getRoot() {
        return root;
    }

    public String serializeToString() {
        return serializeNode(root);
    }

    private String serializeNode(Node node) {
        if (node == null) {
            return "null";
        }
        return "{ \"value\": " + node.getValue() + ", " +
                "\"left\": " + serializeNode(node.getLeft()) + ", " +
                "\"right\": " + serializeNode(node.getRight()) + " }";
    }
}