package com.spring.miniProject.SpringMiniProject;

import com.spring.miniProject.SpringMiniProject.model.Product;
import com.spring.miniProject.SpringMiniProject.service.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ServiceImplTest {
    @Mock
    private Service serviceTest;

    Product product=new Product();
    @Test
    public void Test1(){
        product.setId(1);
        product.setPrice(12);
        product.setQuantity(22);
        product.setName("Salt");
        when(serviceTest.saveProduct(product)).thenReturn(product);
        assertEquals(serviceTest.saveProduct(product),product);
    }
    @Test
    public void Test2(){
        product.setId(1);
        product.setPrice(12);
        product.setQuantity(22);
        product.setName("Salt");
        List<Product> products= Arrays.asList(product);
        when(serviceTest.getAllProducts()).thenReturn(products);
        assertEquals(serviceTest.getAllProducts(),products);
    }
}
