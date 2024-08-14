package com.finalsprint.binarytree;

import com.finalsprint.binarytree.controller.BinaryController;
import com.finalsprint.binarytree.repository.Repo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.finalsprint.binarytree.model.BinaryEntity;
import com.finalsprint.binarytree.model.BinarySearchTree;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BinarytreeApplicationTests {

    @Autowired
    private Repo repo;

    @Test
    void contextLoads() {
    }


    @Test
    void testPagesRender() {
        BinaryController controller = new BinaryController();
        assertThat(controller.home()).isEqualTo("home");
        assertThat(controller.create()).isEqualTo("create");
    }

    @Test
    void testService() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        BinaryEntity entity = new BinaryEntity(tree);
        assertThat(entity.getTreeStructure()).isEqualTo(entity.getTreeStructure());
    }

    @Test
    void testBinarySearchTree() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        assertThat(tree.inOrder().toString()).isEqualTo("[1, 2, 3]");
        assertThat(tree.preOrder().toString()).isEqualTo("[1, 2, 3]");
        assertThat(tree.postOrder().toString()).isEqualTo("[3, 2, 1]");
    }
}
