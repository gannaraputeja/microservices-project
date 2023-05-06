package edu.sjsu.cmpe273.orderservice.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<OrderLineItems> orderLineItems;

}
