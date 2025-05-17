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
	public @ResponseBody String addDish(@RequestBody String descr, @RequestBody String price) {
		Dish dish = new Dish();	
		
		if (descr != null && price != null) {
			float pricePerPortion = 0;

			try {
				pricePerPortion = Float.parseFloat(price);
			} catch (NumberFormatException e) {
				return "Failed to add new dish with error: " + e.getMessage();
			}

			dish.setDishDescription(descr);
			dish.setPricePerPortion(pricePerPortion);

			try {
				dish = dishRepo.save(dish);

				return "Successfully added dish with id: " + dish.getIdd();
			} catch (IllegalArgumentException e) {
				return "Failed to add new dish with error: " + e.getMessage();
			}

		} else {		
			return "Failed to add new dish, one or two arguments are null";
		}
	}
	
	@GetMapping
	public @ResponseBody Iterable<Dish> getDishes() {
		return dishRepo.findAll();
	}
}
