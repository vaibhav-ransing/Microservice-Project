package com.vaibhav.paymentservice.exception;

import com.vaibhav.paymentservice.model.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CustomException.class)
    // Whenever the EmployeeNotFoundException occurs it will be directed to this handler automatically by spring
    @ResponseBody  // This exception will return a response body, so we have to annotate it with this.
    @ResponseStatus(HttpStatus.BAD_REQUEST) // If not defined by default, it will send it as 200 status code
    public ErrorResponse customException(CustomException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}