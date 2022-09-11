package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.ProductType;
import com.example.craftbeerbartmsproject.model.Roles;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.ProductService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    ProductService productService;
    ProducerService producerService;
    UserService userService;

    @Autowired
    public AdminController(ProductService productService, ProducerService producerService, UserService userService) {
        this.productService = productService;
        this.producerService = producerService;
        this.userService = userService;
    }

    @GetMapping("/producer_registration")
    public ModelAndView addProducerPage(@ModelAttribute("producer") Producer producer) {
        ModelAndView view = new ModelAndView();
        List<User> allUsers = userService.findAll();
        view.addObject("userList", allUsers);
        view.setViewName("admin/producerRegistration");
        return view;
    }

    @PostMapping("/producer_registration")
    public ModelAndView addProducer(@ModelAttribute("producer") Producer producer,
                                    @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        producer.setPicture(producerService.saveImage(file));
        producerService.add(producer);
        view.addObject(producer);
        view.setViewName("user/landing");//to-do
        return view;
    }
}
