package com.pizzeria.dtos;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDishDTO extends RepresentationModel<OrderDishDTO>{

    private Integer dishId;
    
    private int quantity;
    
}
