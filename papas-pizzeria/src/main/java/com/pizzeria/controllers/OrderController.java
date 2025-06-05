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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.pizzeria.entities.Client;
import com.pizzeria.entities.Dish;
import com.pizzeria.entities.Order;
import com.pizzeria.entities.OrderDish;
import com.pizzeria.dtos.OrderDTO;
import com.pizzeria.dtos.OrderDishDTO;
import com.pizzeria.dtos.OrderUpdateDTO;
import com.pizzeria.repos.ClientRepository;
import com.pizzeria.repos.DishRepository;
import com.pizzeria.repos.OrderDishRepository;
import com.pizzeria.repos.OrderRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderRepository orderRepo;	
	
	@Autowired
	ClientRepository clientRepo;	
	
	@Autowired
	DishRepository dishRepo;
	
	@Autowired
	OrderDishRepository orderdishRepo;
	
	@PostMapping
	public @ResponseBody String addOrder(@Valid @RequestBody Order order) {
		
		if (order.getOrderedDishes() == null) {        
			return "The orderedDishes field is required";
		}
		
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
	
	@GetMapping("/{ido}")
	public @ResponseBody Object getOrderById(@PathVariable Integer ido) {
	    if (orderRepo.existsById(ido)) {
	        try {
	            Order order = orderRepo.findById(ido)
	                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));

	            return new OrderDTO(order);
	        } catch (IllegalArgumentException e) {
	            return "Failed to get order, error code: " + e.getMessage();
	        }
	    }
	    return "Failed to get order: order with id=" + ido + " does not exist.";
	}
	
	@PutMapping("/{ido}")
	public @ResponseBody String updateOrder(@PathVariable Integer ido, @Valid @RequestBody OrderUpdateDTO orderupdateDTO) {
		    if (orderRepo.existsById(ido)) {
		        Order order = orderRepo.findById(ido).get();
		        
		        try {
		            Integer clientId = orderupdateDTO.getClientId();
		            if (clientId != null) {
		                if (clientRepo.existsById(clientId)) {
		                    order.setClient(clientRepo.findById(clientId).get());
		                } else {
		                    return "Failed to update order: client with ID " + clientId + " does not exist.";
		                }
		            }
		            
		            String newInfo = orderupdateDTO.getAdditionalInfo();
		            if (newInfo != null) {
		                order.setAdditionalInfo(newInfo);
		            }

		            if (orderupdateDTO.getOrderedDishes() != null && !orderupdateDTO.getOrderedDishes().isEmpty()) {
		             
		                for (OrderDish od : order.getOrderedDishes()) {
		                    orderdishRepo.deleteById(od.getId());
		                }
		                order.getOrderedDishes().clear();

		                for (OrderDishDTO odDTO : orderupdateDTO.getOrderedDishes()) {
		                			                	
		                	Dish dish = dishRepo.findById(odDTO.getDishId()).orElse(null);
		                	if (dish != null)
		                	{		                	
			                	OrderDish newOd = new OrderDish(dish, odDTO.getQuantity(), order);	
			                	orderdishRepo.save(newOd);
			                	order.getOrderedDishes().add(newOd);
		                	}
		                	else {
		                		return "Dish with ID " + odDTO.getDishId() + "not found";
		                	}
		                }
		            }

		            orderRepo.save(order);
		        } catch (IllegalArgumentException e) {
		            return "Failed to update the order with error: " + e.getMessage();
		        }

		        return "Successfully updated the order with ID: " + order.getIdo();
		    }

		    return "Failed to update order: Order with given ID doesn't exist.";
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
