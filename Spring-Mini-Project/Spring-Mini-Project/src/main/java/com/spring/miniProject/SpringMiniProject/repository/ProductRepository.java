package com.spring.miniProject.SpringMiniProject.repository;

import com.spring.miniProject.SpringMiniProject.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Integer> {
}
