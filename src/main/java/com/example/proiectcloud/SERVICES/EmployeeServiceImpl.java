package com.example.proiectcloud.SERVICES;

import com.example.proiectcloud.DOMAIN.Employee;
import com.example.proiectcloud.EXCEPTIONS.ResourceNotFoundException;
import com.example.proiectcloud.REPOSITORIES.EmployeeRepository;
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
public class EmployeeServiceImpl  implements  EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Employee> findAll(int pageNumber, String sortField, String sortDirection) {

        Sort sort=Sort.by(sortField);
        sort=sortDirection.equals("asc") ? sort.ascending(): sort.descending();
        Pageable pageable= PageRequest.of(pageNumber-1,3,sort);

        List<Employee> employees=new LinkedList<>();
        employeeRepository.findAll().iterator().forEachRemaining(employees::add);
        log.info("Method findAll from EmployeRepository was called: ");
        employees.forEach(employee -> log.info(employee.getFirstName()));

        return employeeRepository.findAll(pageable);
    }
    @Override
    public List<Employee> findAll() {
        List<Employee> employees=new LinkedList<>();
        employeeRepository.findAll().iterator().forEachRemaining(employees::add);
        return employees;
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> ok=employeeRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Employee not found!");
        }
        log.info("Method findById from EmployeRepository was called for the employe with id:"+id);

        return ok.get();
    }

    @Override
    public Employee save(Employee employee) {
        Employee savedEmployee=employeeRepository.save(employee);
        log.info("Method save from EmployeRepository was called to add new employe:"+employee.getFirstName());

        return savedEmployee;
    }

    @Override
    public void deleteById(long id) {

        Optional<Employee> ok=employeeRepository.findById(id);
        if(!ok.isPresent()){
            throw new ResourceNotFoundException("Employee not found!");
        }
        log.info("Method deleteById from EmployeRepository was called to delete the employe with id:"+id);

        employeeRepository.deleteById(id);
    }
}
