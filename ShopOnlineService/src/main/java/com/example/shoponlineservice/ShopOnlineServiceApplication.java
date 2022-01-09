package com.example.shoponlineservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopOnlineServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopOnlineServiceApplication.class, args);
    }

}
