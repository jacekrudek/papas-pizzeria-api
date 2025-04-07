package com.pizzeria.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idc;
	private String name;
	private int tableNumber;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Order> orders;
}
