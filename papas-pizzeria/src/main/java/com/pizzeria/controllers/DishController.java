package com.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;

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

	@DeleteMapping("/{idd}")
	public @ResponseBody String deleteDish(@PathVariable Integer idd) {

		if (dishRepo.existsById(idd)) {
			try {
				dishRepo.deleteById(idd);
			} catch (IllegalArgumentException e) {
				return "Failed to delete dish, error code: " + e.getMessage();
			}
			return "Successfully deleted dish with id " + idd;
		}
		
		return "Failed to delete dish, invalid ID";
	}
}
