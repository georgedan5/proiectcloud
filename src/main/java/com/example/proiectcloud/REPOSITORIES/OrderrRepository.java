package com.example.proiectcloud.REPOSITORIES;

import com.example.proiectcloud.DOMAIN.Orderr;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderrRepository extends PagingAndSortingRepository<Orderr,Long> {
}
