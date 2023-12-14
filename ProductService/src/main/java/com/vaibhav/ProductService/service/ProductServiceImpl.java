package com.vaibhav.ProductService.service;

import com.vaibhav.ProductService.entity.Product;
import com.vaibhav.ProductService.exception.product.ProductNotFoundException;
import com.vaibhav.ProductService.model.ProductRequest;
import com.vaibhav.ProductService.model.ProductResponse;
import com.vaibhav.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info(productRequest);

        try {
            Product product = Product.builder()
                    .productName(productRequest.getName())
                    .price(productRequest.getPrice())
                    .quantity(productRequest.getQuantity())
                    .build();

            Product savedProduct = productRepository.save(product);
            log.info("Product Created with ID: " + savedProduct.getProductId());
            return savedProduct.getProductId();
        } catch (Exception ex) {
            log.error("Error occurred while adding product: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        log.info(productId);
        System.out.println("Product Id = " + productId);
        Product product
                = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product by id not found for id = "+ productId));

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }
}