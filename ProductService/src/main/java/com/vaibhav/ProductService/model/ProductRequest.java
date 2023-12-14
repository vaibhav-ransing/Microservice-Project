package com.vaibhav.ProductService.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private long price;
    private long quantity;

}
