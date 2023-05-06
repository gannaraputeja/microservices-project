package edu.sjsu.cmpe273.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cart")
@AllArgsConstructor @NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany()
    private List<OrderLineItems> orderLineItems;

}
