package com.vaibhav.orderservice.model;

import com.vaibhav.orderservice.external.response.PaymentResponse;
import com.vaibhav.orderservice.external.response.ProductResponse;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class OrderResponse {

    private long orderId;
    private long amount;
    Instant orderDate;
    private String orderStatus;

    private ProductResponse productDetails;

    private PaymentResponse paymentDetails;
}