package com.vaibhav.orderservice.service;

import com.vaibhav.orderservice.model.OrderRequest;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);
}
