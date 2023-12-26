package com.vaibhav.paymentservice.service;

import com.vaibhav.paymentservice.model.PaymentRequest;

public interface PaymentService {
    Long doPayment(PaymentRequest paymentRequest);
}
