package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Roles;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class UserController {

    UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public ModelAndView landingPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/landing");
        return view;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/login");
        return view;
    }

    @PostMapping(value = "/login")
    public ModelAndView loginUser(@ModelAttribute(name = "user") @NotNull User user) {
        ModelAndView view = new ModelAndView();
        User result = service.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        if (!Objects.equals(result.getFirstName(), "")) {
            view.addObject("userLogin", result );
            view.setViewName("user/profile");
        } else {
            view.setViewName("user/login");
        }
        return view;
    }

    @GetMapping(value = "/registration")
    public ModelAndView registerPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/registration");
        return view;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registerUser(@ModelAttribute("user") User user) {
        ModelAndView view = new ModelAndView();
        user.setRole(Set.of(Roles.USER));
        service.add(user);
        view.setViewName("user/landing");
        return view;
    }

    @GetMapping("/profile/{login}")
    public ModelAndView profileUser(@PathVariable(name = "login") @NotNull User login) {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/profile");
        return view;
    }

    @GetMapping("/shop")
    public ModelAndView shopPage(@ModelAttribute("user") @NotNull User user) {
        ModelAndView view = new ModelAndView();
        view.setViewName("user/shopMenu");
        return view;
    }
}
