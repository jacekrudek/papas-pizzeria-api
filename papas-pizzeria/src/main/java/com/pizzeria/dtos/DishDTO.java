package com.pizzeria.dtos;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.pizzeria.controllers.DishController;
import com.pizzeria.entities.Dish;
import com.pizzeria.entities.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DishDTO extends RepresentationModel<DishDTO> {
    
    private String dishDescription;
    private float pricePerPortion = 0;
    private List<Integer> ingredients = new ArrayList<>();

    public DishDTO(Dish dish) {
        super();
        this.dishDescription = dish.getDishDescription();
        this.pricePerPortion = dish.getPricePerPortion();

        for (Ingredient ing : dish.getIngredients()) {
            this.ingredients.add(ing.getIdi());
        };

        this.add(linkTo(methodOn(DishController.class).updateDish(dish.getIdd(), this)).withSelfRel());
        this.add(linkTo(methodOn(DishController.class).addDish(this)).withSelfRel());
    };
}
