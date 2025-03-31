package com.pizzeria.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{

}
