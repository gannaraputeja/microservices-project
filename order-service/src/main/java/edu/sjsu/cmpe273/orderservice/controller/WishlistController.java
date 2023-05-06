package edu.sjsu.cmpe273.orderservice.controller;

import edu.sjsu.cmpe273.orderservice.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistRepository wishlistRepository;

}
