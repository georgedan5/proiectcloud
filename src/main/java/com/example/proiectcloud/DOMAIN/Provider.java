package com.example.proiectcloud.DOMAIN;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The provider name must be not blank!")
    private String name;
    @NotBlank(message = "The provider city must be not blank!")
    private String city;

    @OneToMany(mappedBy = "provider",cascade = CascadeType.ALL)
    private List<Product> product;
    
    @OneToOne(mappedBy = "provider",orphanRemoval = true)
    Provider_info provider_info;


}