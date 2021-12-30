package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Category;
import com.example.proiectcloud.EXCEPTIONS.ResourceNotFoundException;
import com.example.proiectcloud.REPOSITORIES.CategoryRepository;
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
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> findAll(int pageNumber,String sortField,String sortDirection) {


        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Category> categories = new LinkedList<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories
                ::add);
        log.info("Method findAll from CategoryRepository was called: ");
        categories.forEach(category -> log.info(category.getName()));

        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new LinkedList<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories
                ::add);
        return categories;
    }

    @Override
    public Category findById(long id) {
        Optional<Category> ok=categoryRepository.findById(id);
        if (!ok.isPresent()){
            throw new ResourceNotFoundException("Category not found!");
        }
        log.info("Method findById from CategoryRepository was called for the category with id:"+id);
        return  ok.get();
    }


    @Override
    public Category save(Category category) {
        Category savedcategory=categoryRepository.save(category);
        log.info("Method save from CategoryRepository was called to add new category:"+category.getName());
        return savedcategory;
    }

    @Override
    public void deleteById(long id) {
        Optional<Category> ok=categoryRepository.findById(id);
        if (!ok.isPresent()){
            throw new ResourceNotFoundException("Category not found!");
        }
        log.info("Method deleteById from CategoryRepository was called to delete the category with id:"+id);
        categoryRepository.deleteById(id);
    }
}
