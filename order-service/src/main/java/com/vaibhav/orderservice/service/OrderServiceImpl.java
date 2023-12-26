package com.vaibhav.orderservice.service;

import com.vaibhav.orderservice.entity.Order;
import com.vaibhav.orderservice.external.client.PaymentService;
import com.vaibhav.orderservice.external.client.ProductService;
import com.vaibhav.orderservice.external.request.PaymentRequest;
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

    @Autowired
    private ProductService productService;


    @Autowired
    private PaymentService paymentService;

    @Override
    public Long placeOrder(OrderRequest orderRequest) {
        // Product service -> Block Products (Reduce the Quantity) and if not enough qty then return
        // Order entity -> Save data with status order created
        // Payment Service -> Payments -> Success -> Complete, else Cancel.
        log.info("Calling reduce quantity");
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());


        log.info("Calling place order");
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


        log.info("Calling payment service to complete payment");
        PaymentRequest paymentRequest = PaymentRequest
                .builder()
                .orderId(order.getId())
                .amount(orderRequest.getTotalAmount())
                .referenceNumber("123")
                .paymentMode(orderRequest.getPaymentMode())
                .build();
        log.info("Payment request completed for orderId"+ paymentRequest.getOrderId());
        paymentService.doPayment(paymentRequest);


        return order.getId();
    }
}