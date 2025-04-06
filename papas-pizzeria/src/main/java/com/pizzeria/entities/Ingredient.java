package com.pizzeria.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Table(name = "ingredients")
public class Ingredient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idingr;
    private String ingrDescription;

    // Klasa Dish powinna zawierac skladniki, nie na odwrot
    //@ManyToMany(mappedBy = "dishes")
    //private Dish dish;
}
