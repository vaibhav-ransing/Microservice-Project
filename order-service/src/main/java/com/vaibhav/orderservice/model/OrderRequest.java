package com.vaibhav.orderservice.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderRequest {

    private long productId;
    private long totalAmount;
    private long quantity;

    private PaymentMode paymentMode;
}
