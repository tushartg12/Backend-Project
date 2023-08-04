package com.spring.miniProject.SpringMiniProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ProductIdAlreadyExistsException extends RuntimeException {
    public ProductIdAlreadyExistsException(String s) {
        super(s);
    }
}
