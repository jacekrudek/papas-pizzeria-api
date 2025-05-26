package com.pizzeria.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzeria.entities.Client;
import com.pizzeria.entities.Dish;
import com.pizzeria.entities.Order;
import com.pizzeria.entities.OrderDish;
import com.pizzeria.dtos.OrderDTO;
import com.pizzeria.repos.DishRepository;
import com.pizzeria.repos.OrderRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderRepository orderRepo;	
	
	@Autowired
	DishRepository dishRepo;
	
	@PostMapping
	public @ResponseBody String addOrder(@RequestBody Order order) {
	    try {
	        if (order.getClient() == null || order.getClient().getName() == null || order.getClient().getName().isEmpty()) {
	            return "Failed to add order, error code: client name is required";
	        }

	        if (order.getClient().getTableNumber() < 0) {  
	            return "Failed to add order, error code: table number cannot be negative";
	        }

	        if (order.getOrderedDishes() == null || order.getOrderedDishes().isEmpty()) {
	            return "Failed to add order, error code: order must contain at least one dish";
	        }

	        for (OrderDish od : order.getOrderedDishes()) {
	            if (od.getQuantity() < 1) {
	                return "Failed to add order, error code: dish quantity cannot be lower than 1";
	            }
	            od.setOrder(order);
	        }

	        order = orderRepo.save(order);

	        if (order.getClient().getTableNumber() == 0) {
	            return "Successfully added order with id " + order.getIdo() + " [takeout]";
	        } else {
	            return "Successfully added order with id " + order.getIdo();
	        }

	    } catch (IllegalArgumentException e) {
	        return "Failed to add order, unexpected error: " + e.getMessage();
	    }
	}

	
	@GetMapping("/{ido}/dishes")
	public @ResponseBody Iterable<Dish> getDishesForOrder(@PathVariable Integer ido) {
		Order o = orderRepo.findById(ido).orElse(null);
		List<Dish> dishes = new ArrayList<>();
		
		for (OrderDish orderDishes : o.getOrderedDishes()) {
			dishes.add(orderDishes.getDish());
		}
		
		return dishes;
	}
	
	@GetMapping("{ido}/client")
	public @ResponseBody Client getClientForOrder(@PathVariable Integer ido) {
		Order o = orderRepo.findById(ido).orElse(null);
		
		return o.getClient();
	}
	
	@GetMapping
	public @ResponseBody CollectionModel<OrderDTO> getOrders() {
		List<OrderDTO> ordersDTO = new ArrayList<>();
		
		for (Order order: orderRepo.findAll()) {
			ordersDTO.add(new OrderDTO(order));
		}
		
		return CollectionModel.of(ordersDTO);
	}
}
