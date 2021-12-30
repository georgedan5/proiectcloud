package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Department;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {
    Page<Department> findAll(int pageNumber, String sortField, String sortDirection);
    List<Department> findAll();
    Department findById(long id);
    Department save(Department department);
    void deleteById(long id);
}
