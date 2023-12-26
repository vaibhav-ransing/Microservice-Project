package com.vaibhav.orderservice.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaibhav.orderservice.exception.CustomException;
import com.vaibhav.orderservice.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper = new ObjectMapper();

        log.info("Decoder message "+ s);
        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new CustomException(errorResponse.getMessage(), errorResponse.getHttpStatus());
        } catch (IOException e) {
            throw new CustomException("Internal Server Error", HttpStatus.BAD_REQUEST);
        }
    }

}