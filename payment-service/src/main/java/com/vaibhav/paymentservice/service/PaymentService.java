package com.vaibhav.paymentservice.service;

import com.vaibhav.paymentservice.model.PaymentRequest;
import com.vaibhav.paymentservice.model.PaymentResponse;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long paymentId);
}
