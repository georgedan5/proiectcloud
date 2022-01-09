package com.example.shoponlineservice.CONTROLLERS;

import com.example.shoponlineservice.DOMAIN.Product;
import com.example.shoponlineservice.EXCEPTIONS.AccessDeniedException;
import com.example.shoponlineservice.SERVICES.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;
    @GetMapping("/showLogInForm")
    public String showLogInForm(){ return "login"; }

    @GetMapping("/login-error")public String loginError(Model model) {
        model.addAttribute("errorMessage","Invalid username or password!Please try again!");

        return "login-error";
    }


    @GetMapping("/access_denied")
    public String accessDenied() {
        throw new AccessDeniedException("You are not authorized to access this page!");
       // return "access_denied";
    }

    @RequestMapping({"", "/", "/index"})
    public String productsList(Model model){
        return listByPage(model,1,"id","asc");
    }


    public String listByPage(
            Model model,
            @PathVariable("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Product> page= productService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Product> products=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("products",products);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "products";

    }
}
