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
	    	
	    	 for (OrderDish od : order.getOrderedDishes()) {
	 	    	
	 	        od.setOrder(order);
	 	    }

	        order = orderRepo.save(order);

	            return "Successfully added order with id " + order.getIdo();
	        

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
