package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(int pageNumber, String sortField, String sortDirection);
    List<Product> findAll();
    Product findById(long id);
    Product save(Product product);
    void deleteById(long id);
}
