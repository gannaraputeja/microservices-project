package edu.sjsu.cmpe273.orderservice.repository;

import edu.sjsu.cmpe273.orderservice.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

}
