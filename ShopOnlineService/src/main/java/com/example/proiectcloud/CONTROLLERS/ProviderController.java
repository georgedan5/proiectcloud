package com.example.proiectcloud.CONTROLLERS;

import com.example.proiectcloud.DOMAIN.Provider;
import com.example.proiectcloud.SERVICES.ProviderInfoService;
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
public class ProviderController {

    @Autowired
    ProviderService providerService;
    @Autowired
    ProviderInfoService providerInfoService;

    @RequestMapping("/provider/list")
    public String providersList(Model model) {

    return listByPage(model,1,"id","asc");
    }

    @GetMapping("/provider/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Provider> page= providerService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Provider> providers=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("providers",providers);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "providers";

    }


    @RequestMapping("/provider/delete/{id}")
    public String deleteById(@PathVariable String id){
        providerService.deteleById(Long.valueOf(id));
        return "redirect:/provider/list";
    }

    @RequestMapping("/provider/new")
    public String newProvider(Model model){
        model.addAttribute("provider",new Provider());
        return "providerForm";
    }

    @PostMapping("/provider")
    public String saveOrUpdate(@Valid @ModelAttribute Provider provider, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "providerForm";
        }
        Provider savedProvider=providerService.save(provider);
        return "redirect:/provider/list";
    }

    @RequestMapping("/provider/info/{id}")
    public String showInfo(@PathVariable String id, Model model){
        model.addAttribute("provider",providerInfoService.findById(Long.valueOf(id)));
        return "info";
    }


    //update
    @RequestMapping("/provider/update/{id}")
    public ModelAndView updateProviderById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("providerUpdate");
       Provider providers = providerService.findById(Long.valueOf(id));
        modelAndView.addObject("providerUpdate",providers);
        return modelAndView;
    }


}
