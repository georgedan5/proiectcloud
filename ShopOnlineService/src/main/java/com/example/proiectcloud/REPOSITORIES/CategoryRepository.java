package com.example.proiectcloud.REPOSITORIES;

import com.example.proiectcloud.DOMAIN.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category,Long> {
}
