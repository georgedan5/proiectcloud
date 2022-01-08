package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Orderr;
import com.example.proiectcloud.EXCEPTIONS.ResourceNotFoundException;
import com.example.proiectcloud.REPOSITORIES.OrderrRepository;
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
public class OrderrServiceImpl implements OrderrService {

    @Autowired
    OrderrRepository orderrRepository;

    public OrderrServiceImpl(OrderrRepository orderrRepository) {
        this.orderrRepository = orderrRepository;
    }

    @Override
    public Page<Orderr> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Orderr> orderrs=new LinkedList<>();
        orderrRepository.findAll().iterator().forEachRemaining(orderrs::add);
        log.info("Method findAll from OrderRepository was called: ");
        orderrs.forEach(orderr -> log.info(orderr.getState()));

        return orderrRepository.findAll(pageable);
    }
    @Override
    public List<Orderr> findAll() {
        List<Orderr> orderrs=new LinkedList<>();
        orderrRepository.findAll().iterator().forEachRemaining(orderrs::add);
        return orderrs;
    }

    @Override
    public Orderr findById(long id) {
        Optional<Orderr> ok=orderrRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Order not found!");
        }
        log.info("Method findById from OrderRepository was called for the order with id:"+id);

        return ok.get();
    }

    @Override
    public Orderr save(Orderr orderr) {
        Orderr saveOrderr=orderrRepository.save(orderr);
        log.info("Method save from OrderRepository was called to add new order for customer:"+orderr.getCustomer());

        return saveOrderr;
    }

    @Override
    public void deleteById(long id) {
        Optional<Orderr> ok=orderrRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Order not found!");
        }
        log.info("Method deleteById from OrderRepository was called to delete the order with id:"+id);

        orderrRepository.deleteById(id);
    }
}
