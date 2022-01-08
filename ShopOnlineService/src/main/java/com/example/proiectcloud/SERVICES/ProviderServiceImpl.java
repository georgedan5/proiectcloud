package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Provider;
import com.example.proiectcloud.EXCEPTIONS.ResourceNotFoundException;
import com.example.proiectcloud.REPOSITORIES.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class ProviderServiceImpl  implements ProviderService{


    @Autowired
    ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }




    @Override
    public Page<Provider> findAll(int pageNumber, String sortField, String sortDirection) {


        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Provider> providers=new LinkedList<>();
        providerRepository.findAll().iterator().forEachRemaining(providers::add);
        log.info("Method findAll from ProviderRepository was called: ");
        providers.forEach(provider -> log.info(provider.getName()));

        return providerRepository.findAll(pageable);
    }

    @Override
    public List<Provider> findAll() {
        List<Provider> providers=new LinkedList<>();
        providerRepository.findAll().iterator().forEachRemaining(providers::add);
        return providers;
    }
    @Override
    public Provider save(Provider provider) {
        Provider savedprovider=providerRepository.save(provider);
        log.info("Method save from ProviderRepository was called to add new provider:"+provider.getName());

        return savedprovider;
    }


    @Override
    public Provider findById(long id) {
        Optional<Provider> ok=providerRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Provider not found!");
        }
        log.info("Method findById from ProviderRepository was called for the provider with id:"+id);

        return ok.get();
    }

    @Override
    public void deteleById(long id) {
        Optional<Provider> ok=providerRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Provider not found!");
        }
        log.info("Method deleteById from ProviderRepository was called to delete the provider with id:"+id);

        providerRepository.deleteById(id);
    }
}
