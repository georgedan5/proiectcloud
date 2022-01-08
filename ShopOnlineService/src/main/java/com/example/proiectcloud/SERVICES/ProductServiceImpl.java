package com.example.proiectcloud.SERVICES;


import com.example.proiectcloud.DOMAIN.Product;
import com.example.proiectcloud.EXCEPTIONS.ResourceNotFoundException;
import com.example.proiectcloud.REPOSITORIES.ProductRepository;
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
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;


    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Page<Product> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Product> products=new LinkedList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        log.info("Method findAll from ProductRepository was called: ");
        products.forEach(product -> log.info(product.getName()));

        return productRepository.findAll(pageable);
    }
    @Override
    public List<Product> findAll() {
        List<Product> products=new LinkedList<>();
        productRepository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }
    @Override
    public Product findById(long id) {
        Optional<Product> ok=productRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Product not found!");
        }
        log.info("Method findById from ProductRepository was called for the product with id:"+id);

        return ok.get();

    }

    @Override
    public Product save(Product product) {
        Product savedproduct=productRepository.save(product);
        log.info("Method save from ProductRepository was called to add new product:"+product.getName());

        return  savedproduct;
    }

    @Override
    public void deleteById(long id) {
        Optional<Product> ok=productRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Product not found!");
        }
        log.info("Method deleteById from ProductRepository was called to delete the product with id:"+id);

        productRepository.deleteById(id);
    }

}
