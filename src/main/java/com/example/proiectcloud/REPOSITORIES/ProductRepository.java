package com.example.proiectcloud.REPOSITORIES;

import com.example.proiectcloud.DOMAIN.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

}
