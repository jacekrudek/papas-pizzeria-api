package com.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzeria.entities.Dish;
import com.pizzeria.repos.DishRepository;

@Controller
@RequestMapping("/dishes")
public class DishController {

	@Autowired
	DishRepository dishRepo;
	
	@PostMapping
	public @ResponseBody String addDish(@RequestBody Dish dish) {
		dish = dishRepo.save(dish);
		
		return "Added with id=" + dish.getIdd();
	}
	
	@GetMapping
	public @ResponseBody Iterable<Dish> getDishes() {
		return dishRepo.findAll();
	}
}
