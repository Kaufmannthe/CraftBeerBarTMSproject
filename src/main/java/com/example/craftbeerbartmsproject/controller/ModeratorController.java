package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {
    private final ProducerService producerService;

    @Autowired
    public ModeratorController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/profile")
    public ModelAndView producerProfilePage(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.setViewName("/moderator/profile");
        return view;
    }

}
