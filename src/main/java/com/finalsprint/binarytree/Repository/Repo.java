package com.finalsprint.binarytree.Repository;

import com.finalsprint.binarytree.model.BinaryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Repo extends JpaRepository<BinaryEntity, Long> {

    @PersistenceContext
    EntityManager entityManager = null;

    default BinaryEntity save(BinaryEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    default List<BinaryEntity> findAll() {
        return entityManager.createQuery("SELECT e FROM BinaryEntity e").getResultList();
    }

    default Optional<BinaryEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(BinaryEntity.class, id));
    }
}

