package com.vaibhav.ProductService.exception;

import com.vaibhav.ProductService.exception.product.ProductAlreadyPresentException;
import com.vaibhav.ProductService.exception.product.ProductNotFoundException;
import com.vaibhav.ProductService.exception.product.ProductQuantityNotEnough;
import com.vaibhav.ProductService.model.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse productNotFoundExceptionHandler(ProductNotFoundException exception){
        return new ErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ProductQuantityNotEnough.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse productQuantityNotEnoughExceptionHandler(ProductQuantityNotEnough exception){
        return new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ProductAlreadyPresentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public ErrorResponse productAlreadyPresentHandler(ProductAlreadyPresentException exception){
        return new ErrorResponse(HttpStatus.ALREADY_REPORTED, exception.getMessage());
    }

}