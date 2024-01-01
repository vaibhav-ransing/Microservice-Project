package com.vaibhav.orderservice.external.client;


import com.vaibhav.orderservice.exception.CustomException;
import com.vaibhav.orderservice.external.request.PaymentRequest;
import com.vaibhav.orderservice.external.response.ErrorResponse;
import com.vaibhav.orderservice.external.response.PaymentResponse;
import feign.Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "PAYMENT-SERVICE/payments")
@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@Service
public interface PaymentService {

    @PostMapping   // ("/do")
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);


    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable("orderId") long paymentId);

    default void fallback(Exception e){
        throw new CustomException("Payment Service is not available", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
