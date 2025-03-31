package com.pizzeria.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue
	private int ido;
	private String additionalInfo;
	
//	@OneToMany(mappedBy = "client")
//	private Client client;
}
