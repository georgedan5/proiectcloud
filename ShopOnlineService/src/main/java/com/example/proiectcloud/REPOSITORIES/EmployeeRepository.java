package com.example.proiectcloud.REPOSITORIES;

import com.example.proiectcloud.DOMAIN.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
}
