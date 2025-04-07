package com.pizzeria.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToMany(mappedBy = "ingredients")
    @JsonIgnore
    private List<Dish> dishes;
}
