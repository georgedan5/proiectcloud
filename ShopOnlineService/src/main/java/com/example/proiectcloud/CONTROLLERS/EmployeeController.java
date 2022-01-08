package com.example.proiectcloud.CONTROLLERS;

import com.example.proiectcloud.DOMAIN.Department;
import com.example.proiectcloud.DOMAIN.Employee;
import com.example.proiectcloud.SERVICES.DepartmentService;
import com.example.proiectcloud.SERVICES.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;



    @RequestMapping("/employe/list")
    public String employeeList(Model model){
        return listByPage(model,1,"firstName","asc");
    }

    @GetMapping("/employe/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Employee> page= employeeService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Employee> employee=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("employee",employee);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "employee";

    }

    @RequestMapping("/employe/delete/{id}")
    public String deleteById(@PathVariable String id){
        employeeService.deleteById(Long.valueOf(id));
        return "redirect:/employe/list";
    }

    @RequestMapping("/employe/new")
    public String newEmploye(Model model){
        model.addAttribute("employe",new Employee());
        List<Department> departmentAll=departmentService.findAll();
        model.addAttribute("departmentAll",departmentAll);
        return "employeForm";
    }

    @PostMapping("/employe")
    public String saveOrUpdate(@Valid @ModelAttribute("employe") Employee employee, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "employeForm";
        }
        Employee saveEmploye= employeeService.save(employee);
        return "redirect:/employe/list";
    }
    //update
    @RequestMapping("/employe/update/{id}")
    public ModelAndView updateEmployeById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("employeUpdate");
        Employee employee = employeeService.findById(Long.valueOf(id));
        modelAndView.addObject("employeUpdate",employee);
        List<Department> departmentAll=departmentService.findAll();
        modelAndView.addObject("departmentAll",departmentAll);
        return modelAndView;
    }
}
