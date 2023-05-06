package edu.sjsu.cmpe273.productservice.controller;

import edu.sjsu.cmpe273.productservice.model.Product;
import edu.sjsu.cmpe273.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product Controller
 */
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody Product product) {
        productRepository.save(product);
        return "Product created successfully";
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@RequestBody Product product) {
        productRepository.save(product);
        return "Product updated successfully.";
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return "Product deleted successfully.";
    }


}
