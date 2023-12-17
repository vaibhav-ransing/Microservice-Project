package com.vaibhav.ProductService.service;

import com.vaibhav.ProductService.model.ProductRequest;
import com.vaibhav.ProductService.model.ProductResponse;

public interface ProductService {

    public Long addProduct(ProductRequest productRequest);

    public ProductResponse getProductById(Long id);

    void reduceQuantity(long productId, long quantity);

    void deleteProductById(long productId);
}
