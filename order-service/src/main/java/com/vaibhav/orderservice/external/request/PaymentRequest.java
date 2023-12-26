package com.vaibhav.orderservice.external.request;

import com.vaibhav.orderservice.model.PaymentMode;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentRequest {

    private long orderId;
    private long amount;
    private String referenceNumber;
    PaymentMode paymentMode;

}