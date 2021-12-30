package com.example.proiectcloud.REPOSITORIES;

import com.example.proiectcloud.DOMAIN.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends PagingAndSortingRepository<Customer,Long> {
}
