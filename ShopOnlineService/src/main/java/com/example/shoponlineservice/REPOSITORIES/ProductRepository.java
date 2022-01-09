package com.example.shoponlineservice.REPOSITORIES;

import com.example.shoponlineservice.DOMAIN.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

}
