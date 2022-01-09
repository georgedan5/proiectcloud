package com.example.shoponlineservice.DOMAIN;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Provider_info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String address;
    private String IBAN;

    @OneToOne
    Provider provider;
}
