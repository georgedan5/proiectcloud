package com.example.shoponlineservice.CONTROLLERS;

import com.example.shoponlineservice.DOMAIN.Category;
import com.example.shoponlineservice.SERVICES.CategoryService;
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
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/category/list")
    public String CategoryList(Model model){
           return listByPage(model,1,"name","asc");
    }

    @GetMapping("/category/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Category> page= categoryService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Category> categories=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("categories",categories);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "categories";

    }

    @RequestMapping("/category/delete/{id}")
    public String deleteById(@PathVariable String id){
        categoryService.deleteById(Long.valueOf(id));
        return "redirect:/category/list";
    }

    @RequestMapping("/category/new")
    public String newCategory(Model model){
        model.addAttribute("category",new Category());
        return "categoryForm";
    }

    @PostMapping("/category")
    public String saveOrUpdate(@Valid @ModelAttribute Category category, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "categoryForm";
        }
        Category savedCategory=categoryService.save(category);
        return "redirect:/category/list";
    }

    //update
    @RequestMapping("/category/update/{id}")
    public ModelAndView updateCategoryById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("categoryUpdate");
        Category category = categoryService.findById(Long.valueOf(id));
        modelAndView.addObject("categoryUpdate",category);
        return modelAndView;
    }

}
