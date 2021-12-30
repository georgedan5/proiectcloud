package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Department;
import com.example.proiectcloud.EXCEPTIONS.ResourceNotFoundException;
import com.example.proiectcloud.REPOSITORIES.DepartmentRepository;
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
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Page<Department> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Department> departments=new LinkedList<>();
        departmentRepository.findAll().iterator().forEachRemaining(departments::add);
        log.info("Method findAll from DepartmentRepository was called: ");
        departments.forEach(department -> log.info(department.getName()));

        return departmentRepository.findAll(pageable);
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments=new LinkedList<>();
        departmentRepository.findAll().iterator().forEachRemaining(departments::add);

        return departments;
    }

    @Override
    public Department findById(long id) {
        Optional<Department> ok=departmentRepository.findById(id);
        if(!ok.isPresent()){
            throw  new ResourceNotFoundException("Department not found!");
        }
        log.info("Method findById from DepartmentRepository was called for the department with id:"+id);

        return ok.get();
    }

    @Override
    public Department save(Department department) {
    Department savedDepartment=departmentRepository.save(department);
        log.info("Method save from DepartmentRepository was called to add new department:"+department.getName());

        return savedDepartment;
    }


    @Override
    public void deleteById(long id) {
        Optional<Department> ok=departmentRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Department not found!");
        }
        log.info("Method deleteById from DepartmentRepository was called to delete the department with id:"+id);

        departmentRepository.deleteById(id);
    }
}
