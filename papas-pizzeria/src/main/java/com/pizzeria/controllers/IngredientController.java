package com.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzeria.entities.Ingredient;
import com.pizzeria.repos.IngredientRepository;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    
    @Autowired
    IngredientRepository ingredientRepo;

    @PostMapping
    public @ResponseBody String addIngredient(@RequestBody Ingredient ingredient) {
        ingredient = ingredientRepo.save(ingredient);

        return "Added Ingredient with id=" + ingredient.getIdi();
    }

    @GetMapping
    public @ResponseBody Iterable<Ingredient> getIngredients() {
        return ingredientRepo.findAll();
    }
}
