package com.vaibhav.orderservice.service;

import com.vaibhav.orderservice.entity.Order;
import com.vaibhav.orderservice.model.OrderRequest;
import com.vaibhav.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        // Order entity -> Save data with status order created
        // Product service -> Block Products (Reduce the Quantity)
        // Payment Service -> Payments -> Success -> Complete, else Cancel.

        Order order = Order
                .builder()
                .productId(orderRequest.getProductId())
                .amount(orderRequest.getTotalAmount())
                .quantity(orderRequest.getQuantity())
                .orderDate(Instant.now())
                .orderStatus("CREATED")
                .build();
        order = orderRepository.save(order);
        log.info("Order places successfully with Order Id = "+ order.getId());
        return order.getId();
    }
}

