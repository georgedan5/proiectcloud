package com.example.shoponlineservice.REPOSITORIES;

import com.example.shoponlineservice.DOMAIN.Department;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department,Long> {
}
