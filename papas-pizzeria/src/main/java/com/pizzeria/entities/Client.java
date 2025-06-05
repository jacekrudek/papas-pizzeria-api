package com.pizzeria.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idc;
	
	@NotBlank(message= "no name for client was provided")
	@Valid
	private String name;
	
	@NotNull(message= "no table number for client was provided")
	@Valid
	private int tableNumber;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Order> orders;
}
