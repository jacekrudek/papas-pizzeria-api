package com.pizzeria.dtos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDTO {

	//@JsonIgnore
    private Integer clientId;
	
    private String additionalInfo;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderedDishDTO {
        private Integer dishId;
        private Integer quantity;
    }

    private List<OrderedDishDTO> orderedDishes = new ArrayList<>();
    
    /*
     {
    "clientId": 3,
    "additionalInfo": "Prosze bez cebulki",
    "orderedDishes": [
        { "dishId": 1, "quantity": 2 },
        { "dishId": 3, "quantity": 1 }
    ]
	}
     */
}