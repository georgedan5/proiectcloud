package com.example.proiectcloud.CONTROLLERS;

import com.example.proiectcloud.DOMAIN.*;
import com.example.proiectcloud.SERVICES.CategoryService;
import com.example.proiectcloud.SERVICES.ProductService;
import com.example.proiectcloud.SERVICES.ProviderService;
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
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProviderService providerService;

    @Autowired
    public ProductController(ProductService productService) {
    this.productService=productService;
    }

    //sau folosind ModelAndView
    @RequestMapping("/product/list")
    public String productsList(Model model){
        return listByPage(model,1,"id","asc");
    }

    @GetMapping("/product/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
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


    @RequestMapping("/product/delete/{id}")
    public String deleteById(@PathVariable String id){
        productService.deleteById(Long.valueOf(id));
        return "redirect:/product/list";
    }



    @RequestMapping("/product/new")
    public String newFilm(Model model) {
        List<Category> categoriesAll = categoryService.findAll();
        model.addAttribute("product", new Product());
        model.addAttribute("categoriesAll", categoriesAll);
        List<Provider> providerAll=providerService.findAll();
        model.addAttribute("providerAll",providerAll);
        return "productform";
    }

    @PostMapping("/product")
    public String saveOrUpdate(@Valid  @ModelAttribute Product product, BindingResult bindingResult)
  {
      if (bindingResult.hasErrors()){
          return "productform";
      }
        Product savedProduct=productService.save(product);
        return  "redirect:/product/list";
    }


    //update
    @RequestMapping("/product/update/{id}")
    public ModelAndView updateProductById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("productUpdate");
        Product product = productService.findById(Long.valueOf(id));
        modelAndView.addObject("productUpdate",product);
        List<Category> categoriesAll = categoryService.findAll();
        modelAndView.addObject("categoriesAll", categoriesAll);
        List<Provider> providerAll=providerService.findAll();
        modelAndView.addObject("providerAll",providerAll);
        return modelAndView;
    }
}
