package edu.sjsu.cmpe273.productservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/api/test")
@RequestScope
public class TestController {

    @Value("${test.name}")
    private String name;

    @GetMapping
    public String test() {
        return name;
    }
}
