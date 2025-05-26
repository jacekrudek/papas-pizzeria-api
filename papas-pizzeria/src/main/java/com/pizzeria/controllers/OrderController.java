package com.pizzeria.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.pizzeria.entities.Client;
import com.pizzeria.entities.Dish;
import com.pizzeria.entities.Order;
import com.pizzeria.entities.OrderDish;
import com.pizzeria.dtos.OrderDTO;
import com.pizzeria.repos.DishRepository;
import com.pizzeria.repos.OrderDishRepository;
import com.pizzeria.repos.OrderRepository;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderRepository orderRepo;	
	
	@Autowired
	DishRepository dishRepo;
	
	@Autowired
	OrderDishRepository orderdishRepo;
	
	@PostMapping
	public @ResponseBody String addOrder(@RequestBody Order order) {
		
		
	    for (OrderDish od : order.getOrderedDishes()) {
	    	
	        od.setOrder(order);
	    }

	    order = orderRepo.save(order); 


	    return "Added with id=" + order.getIdo();
	}
	
	
	@DeleteMapping("/{ido}")
	public @ResponseBody String deleteOrder(@PathVariable Integer ido) {
	    if (orderRepo.existsById(ido)) {
	        try {
	            Order order = orderRepo.findById(ido)
	                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

	            List<OrderDish> orderedDishes = new ArrayList<>(order.getOrderedDishes());
	            for (OrderDish od : orderedDishes) {
	            	orderdishRepo.deleteById(od.getId());
	            }

	            orderRepo.delete(order);

	            return "Succesfully deleted order with id=" + ido;
	        } catch (IllegalArgumentException e) {
	            return "Failed to delete order, error code: " + e.getMessage();
	        } catch (Exception e) {
	            return "Unexpected error while deleting order: " + e.getMessage();
	        }
	    }
	    return "Failed to delete order: order with id=" + ido + " does not exist.";
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
