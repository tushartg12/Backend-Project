package com.spring.miniProject.SpringMiniProject.service;

import com.spring.miniProject.SpringMiniProject.Exception.ProductIdAlreadyExistsException;
import com.spring.miniProject.SpringMiniProject.Exception.ProductNotFoundException;
import com.spring.miniProject.SpringMiniProject.model.Order;
import com.spring.miniProject.SpringMiniProject.model.Product;
import com.spring.miniProject.SpringMiniProject.repository.ProductRepository;
import com.spring.miniProject.SpringMiniProject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository prodRepo;

    @Override
    public Product saveProduct(Product product) {
        if(prodRepo.existsById(product.getId())){
            throw new ProductIdAlreadyExistsException("Product with id: "+product.getId()+" already exists");
        }
        else{
            return prodRepo.insert(product);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return prodRepo.findAll();
    }

    @Override
    public Product getProductById(int prodId) {
        Product product= prodRepo.findById(prodId).orElse(null); //.orElse(null) gives null if Id is not collection
        if(product==null){
            //throw new ProductNotFoundException("Product Id: " + prodId + " not exists");
            throw new RuntimeException("Product Id: " + prodId + " not exists");
        }
        return product;
    }

    @Override
    public String deleteProductById(int prodId) {
        if (prodRepo.existsById(prodId)) {
            prodRepo.deleteById(prodId);
            return "Product Id: " + prodId + " deleted successfully";
        } else {
            throw new ProductNotFoundException("Product Id: " + prodId + " not exists");
        }
    }

    @Override
    public Order placeOrder(Order order) {
        //Find product by the product Id in the placed Order
        Product p = prodRepo.findById(order.getProdId()).get();

        //Different actions according to order type
        if (order.getType().equals("purchase")) {
            p.setQuantity(p.getQuantity() + order.getQuantity());
            prodRepo.save(p);
            order.setResult("Purchase Order Placed Successfully");
        } else if (order.getType().equals("sale")) {
            if (order.getQuantity() <= p.getQuantity()) {
                p.setQuantity(p.getQuantity() - order.getQuantity());
                prodRepo.save(p);
                order.setResult("Sale Order Placed Successfully");
            }
            //If present quantity is less than the sale quantity
            else {
                order.setResult("Order Not Placed: Not Enough Amount Available");
            }
        }
        //Giving unique transaction Id
        long k = new Random().nextLong();
        order.setId(k);

        //Save the transaction to the transaction Database
        orderRepository.save(order);
        return order;
    }

    @Override
    public Product updateProduct(int prodId, Product product) {
        if (prodRepo.existsById(prodId)) {
            prodRepo.save(product);
            return product;
        } else {
            throw new ProductNotFoundException("Product Id: " + prodId + " not exists");
        }
    }
}
