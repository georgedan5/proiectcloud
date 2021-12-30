package com.example.proiectcloud.CONTROLLERS;

import com.example.proiectcloud.DOMAIN.Provider;
import com.example.proiectcloud.DOMAIN.Provider_info;
import com.example.proiectcloud.SERVICES.ProviderInfoService;
import com.example.proiectcloud.SERVICES.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProviderInfoController {
    @Autowired
    ProviderInfoService providerInfoService;
    @Autowired
    ProviderService providerService;

    @RequestMapping("/providerinfo/list")
    public String providersInforList(Model model){
        return listByPage(model,1,"id","asc");
    }

    @GetMapping("/providerinfo/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Provider_info> page= providerInfoService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Provider_info> providersInfo=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("providersInfo",providersInfo);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "providersInfo";

    }


    @RequestMapping("/providerinfo/delete/{id}")
    public String deleteById(@PathVariable String id){
        providerInfoService.deleteById(Long.valueOf(id));
        return "redirect:/providerinfo/list";
    }

    @RequestMapping("/providerinfo/new")
    public String newProviderInfo(Model model){
        model.addAttribute("providerinfo",new Provider_info());
        List<Provider> providers=providerService.findAll();
        model.addAttribute("providers",providers);
        return "providerInfoForm";
    }

    @PostMapping("/providerinfo")
    public String saveOrUpdate(@ModelAttribute Provider_info provider_info){
        Provider_info savedProviderInfo=providerInfoService.save(provider_info);
        return "redirect:/providerinfo/list";
    }
    //update
    @RequestMapping("/providerinfo/update/{id}")
    public ModelAndView updateProviderInfoById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("providerinfoUpdate");
        Provider_info provider_info = providerInfoService.findById(Long.valueOf(id));
        modelAndView.addObject("providerinfoUpdate",provider_info);
        List<Provider> providers=providerService.findAll();
        modelAndView.addObject("providers",providers);
        return modelAndView;
    }
}
