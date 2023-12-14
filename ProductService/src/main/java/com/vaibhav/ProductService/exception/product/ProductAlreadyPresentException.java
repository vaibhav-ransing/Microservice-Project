package com.vaibhav.ProductService.exception.product;

public class ProductAlreadyPresentException extends RuntimeException{
    public ProductAlreadyPresentException(String message){
        super(message);
    }
}
