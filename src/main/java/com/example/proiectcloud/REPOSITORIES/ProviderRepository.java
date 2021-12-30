package com.example.proiectcloud.REPOSITORIES;

import com.example.proiectcloud.DOMAIN.Provider;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository  extends PagingAndSortingRepository<Provider,Long> {
}
