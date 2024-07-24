package com.finalsprint.binarytree;

import com.finalsprint.binarytree.Repository.Repo;
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
	private Repo Repository;

	@Test
	void contextLoads() {
	}

	@Test
	void testBinarySearchTreeInsertion() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(5);
		bst.insert(3);
		bst.insert(7);

		assertThat(bst.getRoot().getValue()).isEqualTo(5);
		assertThat(bst.getRoot().getLeft().getValue()).isEqualTo(3);
		assertThat(bst.getRoot().getRight().getValue()).isEqualTo(7);
	}

	@Test
	void testBinarySearchTreeBalancing() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(5);
		bst.insert(3);
		bst.insert(7);
		bst.insert(1);
		bst.insert(4);
		bst.insert(6);
		bst.insert(8);

		bst.balance();

		assertThat(bst.getRoot().getValue()).isEqualTo(5);
		assertThat(bst.getRoot().getLeft().getValue()).isEqualTo(3);
		assertThat(bst.getRoot().getRight().getValue()).isEqualTo(7);
		assertThat(bst.getRoot().getLeft().getLeft().getValue()).isEqualTo(1);
		assertThat(bst.getRoot().getLeft().getRight().getValue()).isEqualTo(4);
		assertThat(bst.getRoot().getRight().getLeft().getValue()).isEqualTo(6);
		assertThat(bst.getRoot().getRight().getRight().getValue()).isEqualTo(8);
	}

	@Test
	void testRepository() {
		BinaryEntity entity = new BinaryEntity();
		entity.setInputNumbers("1,2,3");
		entity.setTreeJson("{\"value\": 2, \"left\": {\"value\": 1, \"left\": null, \"right\": null}, \"right\": {\"value\": 3, \"left\": null, \"right\": null}}");
		Repository.save(entity);

		Optional<BinaryEntity> optionalEntity = Repository.findById(entity.getId());
		assertThat(optionalEntity.isPresent()).isTrue();
		BinaryEntity foundEntity = optionalEntity.get();
		assertThat(foundEntity.getInputNumbers()).isEqualTo("1,2,3");
		assertThat(foundEntity.getTreeJson()).isEqualTo("{\"value\": 2, \"left\": {\"value\": 1, \"left\": null, \"right\": null}, \"right\": {\"value\": 3, \"left\": null, \"right\": null}}");
	}
}
