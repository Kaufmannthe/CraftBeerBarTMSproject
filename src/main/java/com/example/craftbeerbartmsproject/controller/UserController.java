package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.*;
import com.example.craftbeerbartmsproject.service.CartService;
import com.example.craftbeerbartmsproject.service.ProductService;
import com.example.craftbeerbartmsproject.service.UserService;
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

    UserService userService;
    ProductService productService;

    CartService cartService;


    public UserController(UserService service, ProductService productService, CartService cartService) {
        this.userService = service;
        this.productService = productService;
        this.cartService = cartService;
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
        List<Roles> rolesList = new ArrayList<>() {
        };
        rolesList.add(Roles.USER);
        rolesList.add(Roles.ADMIN);
        view.addObject("roleList", rolesList);
        view.setViewName("user/registration");
        return view;
    }

    @PostMapping(value = "/registration")
    public ModelAndView registerUser(@ModelAttribute("user") User user,
                                     @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        if (!Objects.equals(file.getOriginalFilename(), "")) {
            user.setPicture(userService.saveImage(file));
        }
        userService.add(user);
        view.setViewName("user/landing");
        return view;
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView profileUser(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        User user = getUser(authentication);

        view.addObject("userLogin", user);
        view.setViewName("user/profile");
        return view;
    }

    @GetMapping("/shop")
    public ModelAndView shopPage(@ModelAttribute("user") @NotNull User user) {
        ModelAndView view = new ModelAndView();
        Random random = new Random();
        List<Product> resultList = new ArrayList<>();
        Set<Long> set = new LinkedHashSet<>();

        List<Product> listOfProducts = productService.findAll();    //list and int to find boundNumber
        int productCount = productService.findAll().size();

        long originNumber = (int) productService.findAll().stream().findFirst().get().getId();
        long boundNumber = listOfProducts.get(productCount - 1).getId();


        if (productService.findAll().size() >= 4) {
            while (set.size() < 4) {
                long randomId = random.nextLong(originNumber, boundNumber + 1);
                if (productService.findById(randomId).getDataCreated() != null) {
                    set.add(randomId);
                }
            }
        } else {
            while (set.size() < productService.findAll().size()) {
                long randomId = random.nextLong(originNumber, boundNumber + 1);
                if (productService.findById(randomId).getDataCreated() != null) {
                    set.add(randomId);
                }
            }
        }

        for (long i : set) {
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
        long max = listOfProducts.get(productCount - 1).getId();

        view.addObject("min", min);
        view.addObject("max", max);
        view.addObject("product", product);
        view.setViewName("user/product");
        return view;
    }

    @GetMapping("/shop/product/all")
    public ModelAndView allProducts(@ModelAttribute(name = "product") Product product) {
        ModelAndView view = new ModelAndView();

        List<Product> productList = productService.findAll();
        view.addObject("productList", productList);
        view.setViewName("user/all_products");
        return view;
    }

    @GetMapping("/shop/{sort}_list")
    public ModelAndView filtering(@PathVariable(name = "sort") String s) {
        ModelAndView view = new ModelAndView();
        List<Product> productList = new ArrayList<>();
        List<Product> allProducts = productService.findAll();

        for (Product p : allProducts) {
            if (p.getType().name().toLowerCase().equals(s)) {
                productList.add(p);
            }
        }
        view.addObject("productList", productList);
        view.setViewName("user/productFilter");
        return view;
    }

    @GetMapping("/cart")
    public ModelAndView cart(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        User user = getUser(authentication);

        List<Cart> cartList = new ArrayList<>(cartService.all(user.getId()));

        List<Product> productList = new ArrayList<>();

        for (Cart i : cartList){
            productList.add(i.getProduct());
        }

        view.addObject("listOfProducts", productList);

        view.setViewName("/user/cart");
        return view;
    }


    @PostMapping("/cart")
    public ModelAndView addCart(Authentication authentication, @ModelAttribute("product") Product product) {
        ModelAndView view = new ModelAndView();
        User user = getUser(authentication);
        cartService.add(product, user);
        view.setViewName("/user/shopMenu");
        return view;
    }

    private User getUser(Authentication authentication) {
        String userName = authentication.getName();
        return userService.findByLogin(userName);
    }

}
