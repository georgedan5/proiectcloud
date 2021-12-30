package com.example.proiectcloud.CONTROLLERS;

import com.example.proiectcloud.DOMAIN.Customer;
import com.example.proiectcloud.SERVICES.CustomerService;
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
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping("/customer/list")
    public String customersList(Model model){
        return listByPage(model,1,"firstName","asc");
    }


    @GetMapping("/customer/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Customer> page= customerService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Customer> customers=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("customers",customers);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "customers";

    }


    @RequestMapping("/customer/delete/{id}")
    public String deleteById(@PathVariable String id){
        customerService.deleteById(Long.valueOf(id));
        return "redirect:/customer/list";
    }

    @RequestMapping("/customer/new")
    public String newCustomer(Model model){
        model.addAttribute("customer",new Customer());
        return "customerForm";
    }

    @PostMapping("/customer")
    public String saveOrUpdate(@Valid  @ModelAttribute Customer customer, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "customerForm";
        }
        Customer savedCustomer=customerService.save(customer);
        return "redirect:/customer/list";
    }

    //update
    @RequestMapping("/customer/update/{id}")
    public ModelAndView updateCustomerById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("customerUpdate");
        Customer customer = customerService.findById(Long.valueOf(id));
        modelAndView.addObject("customerUpdate",customer);
        return modelAndView;
    }
}
