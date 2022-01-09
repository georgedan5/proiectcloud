package com.example.shoponlineservice.SERVICES;

import com.example.shoponlineservice.DOMAIN.Customer;
import com.example.shoponlineservice.EXCEPTIONS.ResourceNotFoundException;
import com.example.shoponlineservice.REPOSITORIES.CustomerRepository;
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
public class CustomerServiceImpl  implements   CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Page<Customer> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);
        log.info("Method findAll from CustomerRepository was called: ");
        List<Customer> customers=new LinkedList<>();
        customerRepository.findAll().iterator().forEachRemaining(customers::add);
        customers.forEach(customer -> log.info(customer.getFirstName()));
        return customerRepository.findAll(pageable);

    }
    @Override
    public List<Customer> findAll() {
        List<Customer> customers=new LinkedList<>();
        customerRepository.findAll().iterator().forEachRemaining(customers::add);
        return customers;
    }


    @Override
    public Customer findById(long id) {
        Optional<Customer> ok=customerRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Customer not found!");
        }
        log.info("Method findById from CustomerRepository was called for the customer with id:"+id);

        return ok.get();
    }

    @Override
    public Customer save(Customer customer) {
        Customer savedCustomer=customerRepository.save(customer);
        log.info("Method save from CustomerRepository was called to add new customer:"+customer.getFirstName());

        return savedCustomer;
    }

    @Override
    public void deleteById(long id) {
        Optional<Customer> ok=customerRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Customer not found!");
        }
        log.info("Method deleteById from CustomerRepository was called to delete the customer with id:"+id);

        customerRepository.deleteById(id);
    }
}
