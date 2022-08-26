package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModeratorController {

    UserService service;

    @Autowired
    public ModeratorController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView productRegistrationPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("moderator/productRegistration");
        return null;
    }
    public ModelAndView productRegistration(@ModelAttribute ("product")Product product){
        ModelAndView view = new ModelAndView();
        return view;//
    }

}
