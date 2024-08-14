package com.finalsprint.binarytree.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class BinaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String treeStructure;

    public BinaryEntity() {
    }

    public BinaryEntity(BinarySearchTree tree) {
        this.treeStructure = tree.serializeToString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTreeStructure() {
        return treeStructure;
    }

    public void setTreeStructure(String treeStructure) {
        this.treeStructure = treeStructure;
    }

    public void setTree(BinarySearchTree tree) {
        this.treeStructure = tree.serializeToString();
    }
}
