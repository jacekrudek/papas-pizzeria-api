package com.pizzeria.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.pizzeria.entities")
public class PapasPizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PapasPizzeriaApplication.class, args);
	}

}
