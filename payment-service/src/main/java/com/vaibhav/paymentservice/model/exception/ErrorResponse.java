package com.vaibhav.paymentservice.model.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ErrorResponse {
    private HttpStatus httpStatus;
    private String message;
}
