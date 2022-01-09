package com.example.shoponlineservice.CONTROLLERS;

import com.example.shoponlineservice.DOMAIN.*;
import com.example.shoponlineservice.PROXY.NotificationServiceProxy;
import com.example.shoponlineservice.SERVICES.CustomerService;
import com.example.shoponlineservice.SERVICES.EmployeeService;
import com.example.shoponlineservice.SERVICES.NotificationService;
import com.example.shoponlineservice.SERVICES.OrderrService;
import com.example.shoponlineservice.SERVICES.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderrController {

    @Autowired
    OrderrService orderrService;
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ProductService productService;
    @Autowired
    NotificationService notificationService;

    @RequestMapping("/order/list")
    public String ordersList(Model model){
     return listByPage(model,1,"id","asc");
    }

    @GetMapping("/order/list/page/{pageNumber}")
    public String listByPage(
            Model model,
            @PathVariable ("pageNumber") int currentPage,
            @Param("sortField") String sortField,
            @Param("sortDirection") String sortDirection)
    {
        Page<Orderr> page= orderrService.findAll(currentPage,sortField,sortDirection);
        long totalItems= page.getTotalElements();
        int totalPages= page.getTotalPages();
        List<Orderr> orderrs=page.getContent();

        model.addAttribute("currentPage",currentPage);
        model.addAttribute("orderrs",orderrs);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        String reversesortDir=sortDirection.equals("asc") ? "desc" :"asc";
        model.addAttribute("reversesortDir",reversesortDir);

        return "orderrs";

    }


    @RequestMapping("/order/info/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("order",orderrService.findById(Long.valueOf(id)));
        return "orderinfo";
    }



    @RequestMapping("/order/delete/{id}")
    public String deleteById(@PathVariable String id){
        orderrService.deleteById(Long.valueOf(id));
        return "redirect:/order/list";
    }




    @RequestMapping("/order/new")
    public String newOrder(Model model){
        model.addAttribute("order",new Orderr());
        List<Customer> customers=customerService.findAll();
        model.addAttribute("customers",customers);
        List<Employee> employee=employeeService.findAll();
        model.addAttribute("employee",employee);

        //order-details
        List<Product> productAll=productService.findAll();
        model.addAttribute("productAll",productAll);

        return "orderForm";
    }
    @PostMapping("/order")
    public String saveOrUpdate(@ModelAttribute Orderr orderr){
        Orderr savedOrder=orderrService.save(orderr);
        notificationService.sendNotificationToUser(savedOrder);
        return "redirect:/order/list";
    }

    //update
    /*
    @RequestMapping("/order/update/{id}")
    public ModelAndView updateOrderById(@PathVariable String id){
        ModelAndView modelAndView = new ModelAndView("orderUpdate");
        Orderr orderr = orderrService.findById(Long.valueOf(id));
        modelAndView.addObject("orderUpdate",orderr);
        List<Employee> employee=employeeService.findAll();
        modelAndView.addObject("employee",employee);
        List<Customer> customers=customerService.findAll();
        modelAndView.addObject("customers",customers);
        List<Product> productAll=productService.findAll();
        modelAndView.addObject("productAll",productAll);
        return modelAndView;
    }

     */

}
