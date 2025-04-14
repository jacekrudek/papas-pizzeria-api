package com.pizzeria.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "orders_dishes")
public class OrderDish {
	

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @JsonIgnore
	 private int id;

	 @ManyToOne
	 @JoinColumn(name = "dishes_idd")
	 private Dish dish;
	 
	 @ManyToOne
	 @JoinColumn(name = "orders_ido", nullable = false)
	 @JsonIgnore
	 private Order order;


     @Column(nullable = false)
     private int quantity;
}