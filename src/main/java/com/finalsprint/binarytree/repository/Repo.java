package com.finalsprint.binarytree.repository;

import com.finalsprint.binarytree.model.BinaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<BinaryEntity, Long> {
}

