package com.example.proiectcloud.DOMAIN;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The customer first name must be not blank!")
    private String firstName;
    @NotBlank(message = "The customer last name must be not blank!")
    private String lastName;
    @NotBlank(message = "The customer city must be not blank!")
    private String city;
    @NotBlank(message = "The customer street must be not blank!")
    private String street;
    @NotBlank(message = "The customer street number must be not blank!")
    private String streetNumber; //e.g :1B or 23 or 76G etc
    @NotBlank(message = "The customer phone number must be not blank!")
    private String phone;
    @NotBlank(message = "The customer email must be not blank!")
    private String email;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
     private List<Orderr> orderList;
}