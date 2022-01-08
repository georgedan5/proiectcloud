package com.example.proiectcloud.DOMAIN;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The category name must be not blank!")
    private String name;
    @NotBlank(message = "The category description must be not blank!")
    private String description;

   @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
   private List<Product> product;

}