package com.example.shoponlineservice.CONTROLLERS;

import com.example.shoponlineservice.DOMAIN.Department;
import com.example.shoponlineservice.SERVICES.DepartmentService;
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
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/department/list")
    public String departmentsList(Model model){
        return listByPage(model,1,"name","asc");
    }


    @GetMapping("/department/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Department> page= departmentService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Department> departments=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("departments",departments);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "departments";

    }


    @RequestMapping("/department/delete/{id}")
    public String deleteById(@PathVariable String id){
        departmentService.deleteById(Long.valueOf(id));
        return "redirect:/department/list";
    }

    @RequestMapping("/department/new")
    public String newDepartment(Model model){
        model.addAttribute("department",new Department());
        return "departmentForm";
    }

    @PostMapping("/department")
    public String saveOrUpdate(@Valid  @ModelAttribute Department department, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "departmentForm";
        }
        Department saveDepartment=departmentService.save(department);
        return "redirect:/department/list";
    }

    //update
    @RequestMapping("/department/update/{id}")
    public ModelAndView updateDepartmentById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("departmentUpdate");
        Department department = departmentService.findById(Long.valueOf(id));
        modelAndView.addObject("departmentUpdate",department);
        return modelAndView;
    }
}
