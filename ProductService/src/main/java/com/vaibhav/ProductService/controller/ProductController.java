package com.vaibhav.ProductService.controller;

import com.vaibhav.ProductService.model.ProductRequest;
import com.vaibhav.ProductService.model.ProductResponse;
import com.vaibhav.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
        long productId = productService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteProductById(@PathVariable("id") long productId){
        productService.deleteProductById(productId);
        return new ResponseEntity<>( productId, HttpStatus.CREATED);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity){
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PutMapping("/reduceQuantity/{id}/{qty}")
//    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @PathVariable("qty") long quantity) {
//        productService.reduceQuantity(productId, quantity);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId) {
        ProductResponse productResponse =  productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

}