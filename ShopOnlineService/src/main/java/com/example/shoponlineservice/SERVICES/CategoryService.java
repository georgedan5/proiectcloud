package com.example.shoponlineservice.SERVICES;

import com.example.shoponlineservice.DOMAIN.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<Category> findAll(int pageNumber, String sortField,String sortDirection);
    List<Category>findAll();
    Category findById(long id);
    Category save(Category category);
    void deleteById(long id);
}
