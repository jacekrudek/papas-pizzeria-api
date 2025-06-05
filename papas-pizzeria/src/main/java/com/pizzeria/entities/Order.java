package com.pizzeria.entities;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ido;
	
	@NotNull(message = "Additional info is required")
	private String additionalInfo;
	
	@ManyToOne
	@NotNull(message = "Client is required")
    @Valid
	private Client client;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JoinColumn(name = "orders_ido", referencedColumnName = "ido")
	@NotEmpty(message = "Ordered dishes field must exist")
    @Valid
	private List<OrderDish> orderedDishes;
	
}
