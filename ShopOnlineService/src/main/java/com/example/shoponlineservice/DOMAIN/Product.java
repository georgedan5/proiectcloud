package com.example.shoponlineservice.DOMAIN;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The product name must be not blank!")
    private String name;
    @Min(value = 0,message = "The product price must be greater than 0.")
    private double price ;
    @NotBlank(message = "The product name must be not blank!")
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Provider provider;

    @ManyToMany(mappedBy = "prods",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    private List<Orderr> orderrs;





}