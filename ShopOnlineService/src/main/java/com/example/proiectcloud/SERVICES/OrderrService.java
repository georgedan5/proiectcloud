package com.example.proiectcloud.SERVICES;


import com.example.proiectcloud.DOMAIN.Orderr;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderrService {
    Page<Orderr> findAll(int pageNumber, String sortField, String sortDirection);
    List<Orderr> findAll();
    Orderr findById(long id);
    Orderr save(Orderr orderr);
    void deleteById(long id);
}
