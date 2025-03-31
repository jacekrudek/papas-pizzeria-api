package com.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzeria.entities.Order;
import com.pizzeria.repos.OrderRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderRepository orderRepo;
	
	@PostMapping
	public @ResponseBody String addOrder(@RequestBody Order order) {
		order = orderRepo.save(order);
		
		return "Added with id=" + order.getIdo();
	}
	
	@GetMapping
	public @ResponseBody Iterable<Order> getOrders() {
		return orderRepo.findAll();
	}
}
