package com.pizzeria.dtos;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.pizzeria.controllers.OrderController;
import com.pizzeria.entities.Order;
import com.pizzeria.entities.OrderDish;

import lombok.*;

@Getter
public class OrderDTO extends RepresentationModel<OrderDTO>{
	
	public OrderDTO(Order order) {
		super();
		
		this.ido = order.getIdo();
		this.additionalInfo = order.getAdditionalInfo();
		this.clientName = order.getClient().getName();
		
		for (OrderDish orderDish: order.getOrderedDishes()) {
			dishes.add(new AbstractMap.SimpleEntry<>( orderDish.getDish().getDishDescription(), orderDish.getQuantity()));
		}
		
		this.add(linkTo(methodOn(OrderController.class).getClientForOrder(order.getIdo())).withRel("client"));
		this.add(linkTo(methodOn(OrderController.class).getDishesForOrder(order.getIdo())).withRel("dishes"));
	}
		
	private Integer ido;
	private String clientName;
	private String additionalInfo;
	private List<AbstractMap.SimpleEntry<String, Integer>> dishes = new ArrayList<>();
}
