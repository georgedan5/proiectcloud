package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Provider;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProviderService {
    Page<Provider> findAll(int pageNumber, String sortField, String sortDirection);
    List<Provider> findAll();
    Provider save(Provider provider);
    Provider findById(long id);
    void deteleById(long id);
}
