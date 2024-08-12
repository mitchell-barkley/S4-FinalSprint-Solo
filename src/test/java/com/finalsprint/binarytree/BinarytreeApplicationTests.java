package com.finalsprint.binarytree;

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
		entity.setInputNumbers("5,3,7,1,4,6,8");
		entity.setTreeJson("{\"value\":5,\"left\":{\"value\":3,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":4,\"left\":null,\"right\":null}},\"right\":{\"value\":7,\"left\":{\"value\":6,\"left\":null,\"right\":null},\"right\":{\"value\":8,\"left\":null,\"right\":null}}}");
		Repository.save(entity);

		Optional<BinaryEntity> optionalEntity = Repository.findById(entity.getId());
		assertThat(optionalEntity.isPresent()).isTrue();
		BinaryEntity foundEntity = optionalEntity.get();
		assertThat(foundEntity.getInputNumbers()).isEqualTo("5,3,7,1,4,6,8");
		assertThat(foundEntity.getTreeJson()).isEqualTo("{\"value\":5,\"left\":{\"value\":3,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":4,\"left\":null,\"right\":null}},\"right\":{\"value\":7,\"left\":{\"value\":6,\"left\":null,\"right\":null},\"right\":{\"value\":8,\"left\":null,\"right\":null}}}");
	}

	@Test
	void testDataBase() {
		BinaryEntity entity = new BinaryEntity();
		entity.setInputNumbers("5,3,7,1,4,6,8");
		entity.setTreeJson("{\"value\":5,\"left\":{\"value\":3,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":4,\"left\":null,\"right\":null}},\"right\":{\"value\":7,\"left\":{\"value\":6,\"left\":null,\"right\":null},\"right\":{\"value\":8,\"left\":null,\"right\":null}}}");
		Repository.save(entity);

		Optional<BinaryEntity> optionalEntity = Repository.findById(entity.getId());
		assertThat(optionalEntity.isPresent()).isTrue();
		BinaryEntity foundEntity = optionalEntity.get();
		assertThat(foundEntity.getInputNumbers()).isEqualTo("5,3,7,1,4,6,8");
		assertThat(foundEntity.getTreeJson()).isEqualTo("{\"value\":5,\"left\":{\"value\":3,\"left\":{\"value\":1,\"left\":null,\"right\":null},\"right\":{\"value\":4,\"left\":null,\"right\":null}},\"right\":{\"value\":7,\"left\":{\"value\":6,\"left\":null,\"right\":null},\"right\":{\"value\":8,\"left\":null,\"right\":null}}}");
	}
}
