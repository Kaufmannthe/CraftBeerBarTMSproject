package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.ProductService;
import com.example.craftbeerbartmsproject.service.ShopService;
import com.example.craftbeerbartmsproject.service.UserService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;
    private final ProducerService producerService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public ShopController(ShopService shopService, ProducerService producerService,
                          ProductService productService, UserService userService) {
        this.shopService = shopService;
        this.producerService = producerService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView shopPage(@ModelAttribute("user") @NotNull User user) {
        ModelAndView view = new ModelAndView();
        view.addObject("productList", shopService.randomUniqueProductsList());
        view.setViewName("user/shopMenu");
        return view;
    }

    @GetMapping("/product/{id}")
    public ModelAndView productPage(@PathVariable(name = "id") Product product, Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.addObject("products", productService.findAll());
        view.addObject("product", product);
        view.setViewName("user/product");
        return view;
    }

    @GetMapping("/product/all")
    public ModelAndView allProducts(@ModelAttribute(name = "product") Product product) {
        ModelAndView view = new ModelAndView();
        view.addObject("productList", productService.findAll());
        view.setViewName("user/all_products");
        return view;
    }

    @GetMapping("/{sort}_list")
    public ModelAndView filtering(@PathVariable(name = "sort") String name) {
        ModelAndView view = new ModelAndView();
        view.addObject("productList", shopService.sortProductsByName(name));
        view.setViewName("user/productFilter");
        return view;
    }

    @GetMapping("/product_registration")
    @PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
    public ModelAndView productRegistrationPage(@ModelAttribute("product") Product product,
                                                Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.addObject("listOfProducts", Arrays.asList(ProductType.values()));
        view.addObject("listOfProducers", producerService.findAll());
        view.setViewName("moderator/productRegistration");
        return view;
    }

    @PostMapping("/product_registration")
    @PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
    public ModelAndView productRegistration(@ModelAttribute("product") Product product,
                                            @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        product.setPicture(userService.saveImage(file));
        productService.add(product);
        view.setViewName("/moderator/all_products");
        return view;
    }

}
