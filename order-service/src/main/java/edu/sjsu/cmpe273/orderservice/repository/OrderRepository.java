package edu.sjsu.cmpe273.orderservice.repository;

import edu.sjsu.cmpe273.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
