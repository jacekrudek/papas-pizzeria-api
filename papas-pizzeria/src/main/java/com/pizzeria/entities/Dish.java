package com.pizzeria.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "dishes")
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idd;
	private String dishDescription;
	private float pricePerPortion;
	
	@ManyToMany(mappedBy = "dishes")
	@JsonIgnore
    private List<Order> orders;
	
	@ManyToMany
    private List<Ingredient> ingredients;
}
