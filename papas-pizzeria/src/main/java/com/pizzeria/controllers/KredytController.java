package com.pizzeria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pizzeria.entities.*;

//Template controller from lecture

@Controller
@RequestMapping
public class KredytController {
	@Autowired
	Kredyt1 kredyt1;
	
	@Autowired
	Kredyt2 kredyt2;
	
	@GetMapping("/kredyt1")
	public @ResponseBody Double getKredyt(@RequestParam Double kwota) {
		return kredyt1.obliczRate(kwota, 0.1, 12);
	}
	
	@GetMapping("/kredyt2")
	public @ResponseBody Kredyt2 getKredyt2(@RequestParam Double kwota) {
		kredyt2.obliczRate(kwota, 0.1, 12);		
		return kredyt2;
	}
}
