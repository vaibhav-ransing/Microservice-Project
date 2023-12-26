package com.vaibhav.paymentservice.service;

import com.vaibhav.paymentservice.entity.TransactionDetails;
import com.vaibhav.paymentservice.exception.CustomException;
import com.vaibhav.paymentservice.model.PaymentRequest;
import com.vaibhav.paymentservice.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        try {
            log.info("Recording payment details: {}", paymentRequest);
//            int val = (5/0);
            TransactionDetails transactionDetails = TransactionDetails
                    .builder()
                    .orderId(paymentRequest.getOrderId())
                    .paymentMode(paymentRequest.getPaymentMode().name())
                    .referenceNumber(paymentRequest.getReferenceNumber())
                    .paymentDate(Instant.now())
                    .paymentStatus("SUCCESS")
                    .amount(paymentRequest.getAmount())
                    .build();
            transactionDetailsRepository.save(transactionDetails);

            log.info("Transaciton Completed with Id: {}", transactionDetails.getId());
            return transactionDetails.getId();
        }catch (Exception e){
            throw new CustomException("Payment request failed");
        }

    }
}
