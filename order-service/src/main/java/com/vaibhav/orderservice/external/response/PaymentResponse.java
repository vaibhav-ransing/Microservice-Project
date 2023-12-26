package com.vaibhav.orderservice.external.response;

import com.vaibhav.orderservice.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    private long orderId;
    private long amount;
    private long paymentId;
    private String referenceNumber;
    private PaymentMode paymentMode;
    private String status;
    private Instant paymentDate;

}
