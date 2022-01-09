package com.example.shoponlineservice.REPOSITORIES;

import com.example.shoponlineservice.DOMAIN.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends PagingAndSortingRepository<Customer,Long> {
}
