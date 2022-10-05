package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.ProductService;
import com.example.craftbeerbartmsproject.service.ShopService;
import com.example.craftbeerbartmsproject.service.UserService;
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
@RequestMapping("/moderator")
@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
public class ModeratorController {
    private final ProductService productService;
    private final ProducerService producerService;
    private final UserService userService;
    private final ShopService shopService;

    @Autowired
    public ModeratorController(ProductService productService, ProducerService producerService,
                               UserService userService, ShopService shopService) {
        this.productService = productService;
        this.producerService = producerService;
        this.userService = userService;
        this.shopService = shopService;
    }

    @GetMapping("/product_registration")
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
    public ModelAndView productRegistration(@ModelAttribute("product") Product product,
                                            @RequestParam("imageFile") MultipartFile file) throws IOException {
        ModelAndView view = new ModelAndView();
        product.setPicture(userService.saveImage(file));
        productService.add(product);
        view.setViewName("/moderator/all_products");
        return view;
    }

    @GetMapping("/profile")
    public ModelAndView producerProfilePage(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.setViewName("/moderator/profile");
        return view;
    }

    @GetMapping("/terms")
    public ModelAndView termsPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("moderator/termsOfService");
        return view;
    }

    @GetMapping("/all_products")
    public ModelAndView allProducts(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("products", productService.findProductsByProducer(authentication));
        view.setViewName("/moderator/all_products");
        return view;
    }

    @GetMapping("/product/{id}")
    public ModelAndView productPage(@PathVariable(name = "id") Product product, Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.addObject("product", product);
        view.addObject("products",productService.findProductsByProducer(authentication));
        view.setViewName("moderator/product");
        return view;
    }


}
