package com.pizzeria.entities;

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
	
}
