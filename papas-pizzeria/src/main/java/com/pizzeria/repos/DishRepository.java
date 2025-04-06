package com.pizzeria.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.entities.Dish;

@Repository
public interface DishRepository extends CrudRepository<Dish, Integer>{

}