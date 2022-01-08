package com.example.proiectcloud.REPOSITORIES;

import com.example.proiectcloud.DOMAIN.Provider_info;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Provider_infoRepository  extends PagingAndSortingRepository<Provider_info,Long> {
}
