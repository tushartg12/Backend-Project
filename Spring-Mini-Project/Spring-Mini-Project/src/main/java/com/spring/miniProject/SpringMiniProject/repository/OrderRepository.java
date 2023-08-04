package com.spring.miniProject.SpringMiniProject.repository;

import com.spring.miniProject.SpringMiniProject.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
