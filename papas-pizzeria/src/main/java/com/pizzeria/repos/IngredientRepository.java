package com.pizzeria.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.entities.Ingredient;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
}
