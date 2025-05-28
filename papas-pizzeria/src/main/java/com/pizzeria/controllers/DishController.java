package com.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzeria.dtos.DishDTO;
import com.pizzeria.entities.Dish;
import com.pizzeria.repos.DishRepository;
import com.pizzeria.repos.IngredientRepository;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/dishes")
public class DishController {

	@Autowired
	DishRepository dishRepo;

	@Autowired
	IngredientRepository ingRepo;

	@PostMapping
	public @ResponseBody String addDish(@RequestBody DishDTO dishdto) {
		Dish _dish = new Dish();
		
		if ((dishdto.getDishDescription() != null) && (dishdto.getPricePerPortion() > 0)) {

			_dish.setDishDescription(dishdto.getDishDescription());
			_dish.setPricePerPortion(dishdto.getPricePerPortion());

			try {
				_dish = dishRepo.save(_dish);

				return "Successfully added dish with id: " + _dish.getIdd();
			} catch (IllegalArgumentException e) {
				return "Failed to add new dish with error: " + e.getMessage();
			}
		}
		
		return "Failed to add new dish, one or two arguments are null";
	}
	

	@PutMapping("/{idd}")
	public @ResponseBody String updateDish(@PathVariable Integer idd, @RequestBody DishDTO dishdto) {
		if (dishRepo.existsById(idd)) {
			Dish _dish = dishRepo.findById(idd).get();
			
			try {
				String tmpDescr = dishdto.getDishDescription();
				float tmpPrice = dishdto.getPricePerPortion();

				if (tmpDescr != null) { _dish.setDishDescription(tmpDescr); }
				if (tmpPrice > 0) { _dish.setPricePerPortion(tmpPrice); }

				if (dishdto.getIngredients().size() > 0) {
					_dish.getIngredients().clear();

					for (Integer ingId : dishdto.getIngredients()) {
						if (ingRepo.existsById(ingId)) {
							_dish.getIngredients().add(ingRepo.findById(ingId).get());
						}
					}
				}

				dishRepo.save(_dish);
			} catch (IllegalArgumentException e) {
				return "Failed to update the dish with error: " + e.getMessage();
			}

			return "Successfully updated the dish with ID: " + _dish.getIdd();
		}
		
		return "Failed to update dish: Dish with given ID doesn't exist";
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
	
	@GetMapping
	public @ResponseBody Iterable<Dish> getDishes() {
		return dishRepo.findAll();
	}
}
