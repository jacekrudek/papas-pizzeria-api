package com.pizzeria.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.pizzeria.controllers", "com.pizzeria.repos"})
@EntityScan("com.pizzeria.entities")
@EnableJpaRepositories("com.pizzeria.repos")
public class PapasPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PapasPizzeriaApplication.class, args);
	}

}
