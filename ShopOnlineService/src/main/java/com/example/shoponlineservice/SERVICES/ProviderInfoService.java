package com.example.shoponlineservice.SERVICES;

import com.example.shoponlineservice.DOMAIN.Provider_info;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProviderInfoService {
    Page<Provider_info> findAll(int pageNumber, String sortField, String sortDirection);
    List<Provider_info> findAll();
    Provider_info findById(long id);
    Provider_info save(Provider_info providerInfo);
    void deleteById(long id);
}
