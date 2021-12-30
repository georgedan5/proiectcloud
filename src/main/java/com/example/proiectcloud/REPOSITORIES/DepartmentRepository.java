package com.example.proiectcloud.REPOSITORIES;

import com.example.proiectcloud.DOMAIN.Department;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department,Long> {
}
