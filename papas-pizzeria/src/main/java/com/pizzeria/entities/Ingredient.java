package com.pizzeria.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "ingredients")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idi;
    private String ingDesc;

    //@ManyToMany(mappedBy = "dishes")
    //private Dish dish;
}
