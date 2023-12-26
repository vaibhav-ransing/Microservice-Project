package com.vaibhav.orderservice.service;

import com.vaibhav.orderservice.model.OrderRequest;
import com.vaibhav.orderservice.model.OrderResponse;

public interface OrderService {
    Long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderById(long orderId);
}
