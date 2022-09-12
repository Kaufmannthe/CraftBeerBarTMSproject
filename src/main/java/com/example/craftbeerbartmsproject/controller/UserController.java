package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.*;
import com.example.craftbeerbartmsproject.service.ProductService;
import com.example.craftbeerbartmsproject.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.LongStream;

@Controller
public class UserController {

    UserService service;
    ProductService productService;

    @Autowired
    public UserController(UserService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
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
        User result = service.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
        if (!Objects.equals(result.getFirstName(), "")) {
            view.addObject("userLogin", result);
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
    public ModelAndView registerUser(@ModelAttribute("user") User user,
                                     @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        user.setRole(Set.of(Roles.USER));
        if (!Objects.equals(file.getOriginalFilename(), "")) {
            user.setPicture(service.saveImage(file));
        }
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
        List<Product> listOfProducts = productService.findAll();    //list and int to find boundNumber
        int productCount = productService.findAll().size();

        long originNumber = (int) productService.findAll().stream().findFirst().get().getId();
        long boundNumber = listOfProducts.get(productCount -1).getId();


        Random random = new Random();
        ModelAndView view = new ModelAndView();

        List<Product> resultList = new ArrayList<>();

        long[] longs = random.longs(4, originNumber, boundNumber).toArray();

        for (long i : longs) {
            resultList.add(productService.findById(i));
        }

        view.addObject("productList", resultList);
        view.setViewName("user/shopMenu");
        return view;
    }

    @GetMapping("/shop/product/{id}")
    public ModelAndView productPage(@PathVariable(name = "id") Product product) {
        ModelAndView view = new ModelAndView();

        List<Product> listOfProducts = productService.findAll();    //list and int to find boundNumber
        int productCount = productService.findAll().size();

        long min = (int) productService.findAll().stream().findFirst().get().getId();
        long max = listOfProducts.get(productCount -1).getId();

        view.addObject("min", min);
        view.addObject("max", max);
        view.addObject("product", product);
        view.setViewName("user/product");
        return view;
    }
}
