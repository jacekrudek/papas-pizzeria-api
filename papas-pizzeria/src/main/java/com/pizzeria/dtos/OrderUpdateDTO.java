package com.pizzeria.dtos;

import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.RepresentationModel;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateDTO extends RepresentationModel<OrderUpdateDTO>{

    private Integer clientId;
	
    private String additionalInfo;
	
    private List<OrderDishDTO> orderedDishes;
    
}