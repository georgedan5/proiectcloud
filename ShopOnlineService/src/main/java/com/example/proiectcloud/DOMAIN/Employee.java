package com.example.proiectcloud.DOMAIN;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "The employe first name must be not blank!")
    private String firstName;
    @NotBlank(message = "The employe last name must be not blank!")
    private String lastName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    @NotNull(message = "The employe hire date must be not null!")
    private java.util.Date date;

    @NotBlank(message = "The employe phone number must be not blank!")
    private String phone;
    @NotBlank(message = "The employe email must be not blank!")
    private String email;
    @Min(value =0,message = "The salary must be greater than 0 ")
    private double salary;

    @OneToMany(mappedBy = "employee")
    private List<Orderr> orders;

    @ManyToOne
    @NotNull(message = "nu gol")
    private Department dep;
}
