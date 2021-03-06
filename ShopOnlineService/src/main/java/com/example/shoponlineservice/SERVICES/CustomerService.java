package com.example.shoponlineservice.SERVICES;

import com.example.shoponlineservice.DOMAIN.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    Page<Customer> findAll(int pageNumber, String sortField, String sortDirection);
    List<Customer> findAll();
    Customer findById(long id);
    Customer save(Customer customer);
    void deleteById(long id);
}
