package com.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzeria.entities.Client;
import com.pizzeria.repos.ClientRepository;

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientRepository clientRepo;
	
	@PostMapping
	public @ResponseBody String addClient(@RequestBody Client client) {
		client = clientRepo.save(client);
		
		return "Added with id=" + client.getIdc();
	}
	
	@GetMapping
	public @ResponseBody Iterable<Client> getClient() {
		return clientRepo.findAll();
	}
}

