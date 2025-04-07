package com.pizzeria.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ido;
	private String additionalInfo;
	
	@ManyToOne
	private Client client;
	
	@ManyToMany
	private List<Dish> dishes;
}
