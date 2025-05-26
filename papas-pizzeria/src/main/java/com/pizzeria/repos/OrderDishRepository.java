package com.pizzeria.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.entities.OrderDish;

@Repository
public interface OrderDishRepository extends CrudRepository<OrderDish, Integer>{

}
