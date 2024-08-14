package com.finalsprint.binarytree;

import com.finalsprint.binarytree.model.BinarySearchTree;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.finalsprint.binarytree.controller",
		"com.finalsprint.binarytree.model",
		"com.finalsprint.binarytree.services",
		"com.finalsprint.binarytree.repository",
		"com.finalsprint.binarytree.config"})
@EnableJpaRepositories("com.finalsprint.binarytree.Repository")
@EntityScan("com.finalsprint.binarytree.model")
public class BinarytreeApplication {
	public static void main(String[] args) {
		SpringApplication.run(BinarytreeApplication.class, args);
	}
}