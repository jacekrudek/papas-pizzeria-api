package com.pizzeria.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pizzeria.entities.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer>{

}
