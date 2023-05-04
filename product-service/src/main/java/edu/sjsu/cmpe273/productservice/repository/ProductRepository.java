package edu.sjsu.cmpe273.productservice.repository;

import edu.sjsu.cmpe273.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Product Repository
 */
public interface ProductRepository extends MongoRepository<Product, String> {
}
