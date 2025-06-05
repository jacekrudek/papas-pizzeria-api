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

import com.pizzeria.entities.Client;
import com.pizzeria.repos.ClientRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientRepository clientRepo;
	
	@PostMapping
	public @ResponseBody String addClient(@Valid @RequestBody Client client) {
		
		if (client.getName() == null || client.getName().strip() == "") {
			return "The name of the client has to be provided";
		}
		
		client = clientRepo.save(client);
		
		return "Added with id=" + client.getIdc();
	}
	
	@GetMapping
	public @ResponseBody Iterable<Client> getClient() {
		return clientRepo.findAll();
	}

	@DeleteMapping("/{idc}")
	public @ResponseBody String deleteClient(@PathVariable Integer idc) {
		if (clientRepo.existsById(idc)) {
			try {
				clientRepo.deleteById(idc);
			} catch (IllegalArgumentException e) {
				return "Failed to delete client, error code: " + e.getMessage();
			}
			return "Deleted client with id " + idc;
		}
		
	    return "Failed to delete client";
	}
}

