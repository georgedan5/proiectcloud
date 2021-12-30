package com.example.proiectcloud.DOMAIN;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
public class Orderr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private java.util.Date date;
    private String state;
    private String observation;

    @ManyToOne
     private Customer customer;

    @ManyToOne
   private Employee employee;

  @ManyToMany(//targetEntity = Product.class,
         cascade = {CascadeType.PERSIST,CascadeType.MERGE})
  @JoinTable(name = "orders_details",
         joinColumns = @JoinColumn(name = "id_order", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"))
    private List<Product> prods;



}
