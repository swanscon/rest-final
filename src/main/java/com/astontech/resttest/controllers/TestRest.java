package com.astontech.resttest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRest {

    @GetMapping("/test")
    public String welcomeDevs() {
        return "Welcome back developers!";
    }

    @GetMapping("/exception")
    public void exceptionTest() {
        throw new RuntimeException();
    }
}
