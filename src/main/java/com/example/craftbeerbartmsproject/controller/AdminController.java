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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public ModelAndView addProducerPage(@ModelAttribute("user") User user) {
        ModelAndView view = new ModelAndView();
        User user1 = new User(1, "Artem", "Bohan", "Kaufmannthe", "111", false,
                23, "Minsk",
                "Male", "123", "k@gm.com", Set.of(Roles.USER, Roles.ADMIN, Roles.MODERATOR));
        User user2 = new User(1, "Artem", "Bohan", "Jpa", "111", false,
                23, "Minsk",
                "Male", "123", "k@gm.com", Set.of(Roles.USER, Roles.ADMIN, Roles.MODERATOR));
        User user3 = new User(1, "Artem", "Bohan", "Kek4", "111", false,
                23, "Minsk",
                "Male", "123", "k@gm.com", Set.of(Roles.USER, Roles.ADMIN, Roles.MODERATOR));
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        view.addObject("userList", list);
        view.setViewName("admin/producerRegistration");
        return view;
    }

    @PostMapping("/producer_registration")
    public ModelAndView addProducer(@ModelAttribute("producer") Producer producer) {
        ModelAndView view = new ModelAndView();
        producerService.add(producer);
        view.addObject(producer);
        view.setViewName("user/landing");//to-do
        return view;
    }
}
