package com.vaibhav.ProductService.exception.product;


public class ProductQuantityNotEnough extends RuntimeException{
    public ProductQuantityNotEnough(String message){
        super(message);
    }
}
