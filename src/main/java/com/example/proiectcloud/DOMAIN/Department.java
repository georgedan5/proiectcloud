package com.example.proiectcloud.DOMAIN;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The department name must be not blank!")
    private String name;
    @NotBlank(message = "The department city must be not blank!")
    private String city;

    @OneToMany(mappedBy = "dep",cascade = CascadeType.ALL)
    private List<Employee> employees;

}