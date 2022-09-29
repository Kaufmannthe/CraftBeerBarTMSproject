package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.*;
import com.example.craftbeerbartmsproject.service.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.LongStream;

@Controller
public class UserController {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final RegistrationService registrationService;
    private final ShopService shopService;

    public UserController(UserService userService, ProductService productService,
                          CartService cartService, RegistrationService registrationService,
                          ShopService shopService) {
        this.userService = userService;
        this.productService = productService;
        this.cartService = cartService;
        this.registrationService = registrationService;
        this.shopService = shopService;
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

    @GetMapping(value = "/registration")
    public ModelAndView registerPage(@ModelAttribute("user") User user) {
        ModelAndView view = new ModelAndView();
        view.addObject("roleList", registrationService.rolesForAdminsUserRegistration());
        view.setViewName("user/registration");
        return view;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registerUser(@ModelAttribute("user") User user,
                                     @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        registrationService.pictureCheckUser(user, file);
        userService.add(user);
        view.setViewName("user/landing");
        return view;
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profileUser(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("userLogin", userService.getAuthUser(authentication));
        view.setViewName("user/profile");
        return view;
    }

    @GetMapping("/shop")
    public ModelAndView shopPage(@ModelAttribute("user") @NotNull User user) {
        ModelAndView view = new ModelAndView();
        view.addObject("productList", shopService.randomUniqueProductsList());
        view.setViewName("user/shopMenu");
        return view;
    }


    @GetMapping("/shop/product/{id}")
    public ModelAndView productPage(@PathVariable(name = "id") Product product) {
        ModelAndView view = new ModelAndView();
        view.addObject("min", shopService.minimalIndex());
        view.addObject("max", shopService.maximumIndex());
        view.addObject("product", product);
        view.setViewName("user/product");
        return view;
    }

    @GetMapping("/shop/product/all")
    public ModelAndView allProducts(@ModelAttribute(name = "product") Product product) {
        ModelAndView view = new ModelAndView();
        view.addObject("productList", productService.findAll());
        view.setViewName("user/all_products");
        return view;
    }

    @GetMapping("/shop/{sort}_list")
    public ModelAndView filtering(@PathVariable(name = "sort") String name) {
        ModelAndView view = new ModelAndView();
        view.addObject("productList", shopService.sortProductsByName(name));
        view.setViewName("user/productFilter");
        return view;
    }

    @GetMapping("/cart")
    public ModelAndView cart(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("listOfProducts", productService.findProductsByCarts
                (cartService.findCartsByUsername(userService.getAuthUser(authentication))));
        view.addObject("listOfCarts",
                cartService.findCartsByUsername(userService.getAuthUser(authentication)));
        view.setViewName("/user/cart");
        return view;
    }

    @PostMapping("/cart")
    public ModelAndView addCart(Authentication authentication, @ModelAttribute("product") Product product) {
        ModelAndView view = new ModelAndView();
        cartService.add(product, userService.getAuthUser(authentication));
        view.setViewName("redirect:/shop/product/all");
        return view;
    }

    @GetMapping("/cart/{id}")
    public ModelAndView deleteProductFromCart(@PathVariable(name = "id") Cart cart) {
        ModelAndView view = new ModelAndView();
        cartService.delete(cart);
        view.setViewName("redirect:/cart");
        return view;
    }
}
