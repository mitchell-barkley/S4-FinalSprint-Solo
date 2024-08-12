package com.finalsprint.binarytree.model;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public static BinarySearchTree fromJson(String treeJson) {
        return null;
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

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void balance() {
        List<Integer> values = new ArrayList<>();
        inOrderTraversal(root, values);
        root = sortedArrayToBinaryTree(values, 0, values.size() - 1);
    }

    private void inOrderTraversal(Node node, List<Integer> values) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.getLeft(), values);
        values.add(node.getValue());
        inOrderTraversal(node.getRight(), values);
    }

    private Node sortedArrayToBinaryTree(List<Integer> values, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(values.get(mid));
        node.setLeft(sortedArrayToBinaryTree(values, start, mid - 1));
        node.setRight(sortedArrayToBinaryTree(values, mid + 1, end));

        return node;
    }

    // Method to return JSON representation of the tree (simple representation)
    public String toJson() {
        return toJson(root);
    }

    private String toJson(Node node) {
        if (node == null) {
            return "null";
        }

        return "{ \"value\": " + node.getValue() + ", \"left\": " + toJson(node.getLeft()) + ", \"right\": " + toJson(node.getRight()) + " }";
    }

    // toString
    @Override
    public String toString() {
        return "BinarySearchTree{" + "root=" + root + '}';
    }
}
