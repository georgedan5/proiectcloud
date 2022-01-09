package com.example.shoponlineservice.REPOSITORIES;

import com.example.shoponlineservice.DOMAIN.Provider_info;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Provider_infoRepository  extends PagingAndSortingRepository<Provider_info,Long> {
}
