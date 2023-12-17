package com.vaibhav.ProductService.service;

import com.vaibhav.ProductService.entity.Product;
import com.vaibhav.ProductService.exception.product.ProductNotFoundException;
import com.vaibhav.ProductService.exception.product.ProductQuantityNotEnough;
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
                .orElseThrow(()-> new ProductNotFoundException("Product not found for id = "+ productId));

        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity for id = "+ productId +" with quality = "+ quantity);
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found for id = "+ productId));
        if(product.getQuantity() < quantity){
            throw new ProductQuantityNotEnough("Product does not have enough quantity");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Reduced quantity of "+ quantity +" for product id = "+ productId);

    }

    @Override
    public void deleteProductById(long productId) {
        log.info("Deleting product for productId = "+ productId);
        productRepository.deleteById(productId);
        log.info("Deleted product for productId = "+ productId);

    }
}