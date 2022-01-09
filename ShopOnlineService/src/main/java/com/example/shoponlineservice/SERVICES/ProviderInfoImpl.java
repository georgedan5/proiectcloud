package com.example.shoponlineservice.SERVICES;


import com.example.shoponlineservice.DOMAIN.Provider_info;
import com.example.shoponlineservice.EXCEPTIONS.ResourceNotFoundException;
import com.example.shoponlineservice.REPOSITORIES.Provider_infoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProviderInfoImpl implements  ProviderInfoService {
    @Autowired
    Provider_infoRepository provider_infoRepository;

    public ProviderInfoImpl(Provider_infoRepository provider_infoRepository) {
        this.provider_infoRepository = provider_infoRepository;
    }


    @Override
    public Page<Provider_info> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);
        List<Provider_info> providerInfoList=new LinkedList<>();
        provider_infoRepository.findAll().iterator().forEachRemaining(providerInfoList::add);
        log.info("Method findAll from ProviderInfoRepository was called: ");
        providerInfoList.forEach(provider_info -> log.info(provider_info.getEmail()));
        return provider_infoRepository.findAll(pageable);

    }
    @Override
    public List<Provider_info> findAll() {
        List<Provider_info> providerInfoList=new LinkedList<>();
        provider_infoRepository.findAll().iterator().forEachRemaining(providerInfoList::add);
        return providerInfoList;
    }
    @Override
    public Provider_info findById(long id) {
        Optional<Provider_info> ok=provider_infoRepository.findById(id);
        if (!ok.isPresent()){
            throw new ResourceNotFoundException("ProviderInfo not found!");
        }
        log.info("Method findById from ProviderInfoRepository was called for providerInfo with id:"+id);

        return ok.get();
    }

    @Override
    public Provider_info save(Provider_info providerInfo) {
        Provider_info savedProviderInfo=provider_infoRepository.save(providerInfo);
        log.info("Method save from ProviderInfoRepository was called to add info for a new provider:"+providerInfo.getProvider());

        return savedProviderInfo;
    }

    @Override
    public void deleteById(long id) {
        Optional<Provider_info> ok=provider_infoRepository.findById(id);
        if (!ok.isPresent()){
            throw new ResourceNotFoundException("ProviderInfo not found!");
        }
        log.info("Method deleteById from ProviderInfoRepository was called to delete the providerInfo with id:"+id);

        provider_infoRepository.deleteById(id);
    }
}
