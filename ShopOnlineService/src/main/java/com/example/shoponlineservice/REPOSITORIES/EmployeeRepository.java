package com.example.shoponlineservice.REPOSITORIES;

import com.example.shoponlineservice.DOMAIN.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
}
