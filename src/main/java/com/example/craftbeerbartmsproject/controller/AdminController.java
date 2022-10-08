package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.RegistrationService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    private final ProducerService producerService;
    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public AdminController(ProducerService producerService, UserService userService, RegistrationService registrationService) {
        this.producerService = producerService;
        this.userService = userService;
        this.registrationService = registrationService;
    }

    @GetMapping("/producer_registration")
    public ModelAndView addProducerPage(@ModelAttribute("producer") Producer producer) {
        ModelAndView view = new ModelAndView();
        view.addObject("userList", userService.findAll());
        view.setViewName("admin/producerRegistration");
        return view;
    }

    @PostMapping("/producer_registration")
    public ModelAndView addProducer(@ModelAttribute("producer") Producer producer,
                                    @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        registrationService.pictureCheckProducer(producer, file);
        producerService.add(producer);
        view.setViewName("redirect:/");
        return view;
    }
}
