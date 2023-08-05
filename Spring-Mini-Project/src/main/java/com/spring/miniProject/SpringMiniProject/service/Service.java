package com.spring.miniProject.SpringMiniProject.service;

import com.spring.miniProject.SpringMiniProject.model.Order;
import com.spring.miniProject.SpringMiniProject.model.Product;

import java.util.ArrayList;
import java.util.List;
public interface Service {
    Product saveProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(int prodId);
    String deleteProductById(int prodId);
    Order placeOrder(Order order);
    Product updateProduct(int prodId, Product product);
}
