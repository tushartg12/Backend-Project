package com.spring.miniProject.SpringMiniProject.controller;
import com.spring.miniProject.SpringMiniProject.model.Product;
import com.spring.miniProject.SpringMiniProject.model.Order;
import com.spring.miniProject.SpringMiniProject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private Service service;
    @GetMapping("/")  //Welcome, Page
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<>("Welcome",HttpStatus.OK);
    }
    @GetMapping("/findAllProducts")  //To get all the products
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(),HttpStatus.OK);
    }
    @PostMapping("/addProduct")  //To add a product
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
        //.created() means 201 response code
        return new ResponseEntity<>(service.saveProduct(product),HttpStatus.CREATED); //You can return the URI of the created user

    }

    @GetMapping("/findProductById/{id}")  //to find a product by its iD
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        return new ResponseEntity<>(service.getProductById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")  //to delete a product by its iD
    public ResponseEntity<String> deleteProductById(@PathVariable int id){
        return new ResponseEntity<>(service.deleteProductById(id),HttpStatus.OK);
    }
    @PutMapping("/updateProductById/{id}")  //to change any product detail by its iD
    public ResponseEntity<Product> updateProductById(@PathVariable int id, @RequestBody Product product){
        return new ResponseEntity<>(service.updateProduct(id,product),HttpStatus.CREATED);
    }
    @PostMapping("/orders")  //To place an order
    public ResponseEntity<Order> addTransaction(@Valid @RequestBody Order order){
        return new ResponseEntity<>(service.placeOrder(order),HttpStatus.OK);
    }
}
