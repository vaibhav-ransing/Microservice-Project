package com.vaibhav.ProductService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/")
    public String hello(){
        return "Hello fasdfasd Wold";
    }
}
