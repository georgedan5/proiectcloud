package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Page<Employee> findAll(int pageNumber, String sortField, String sortDirection);
    List<Employee> findAll();
    Employee findById(long id);
    Employee save(Employee employee);
    void deleteById(long id);
}
