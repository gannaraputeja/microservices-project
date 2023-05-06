package edu.sjsu.cmpe273.orderservice.controller;

import edu.sjsu.cmpe273.orderservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;

}
