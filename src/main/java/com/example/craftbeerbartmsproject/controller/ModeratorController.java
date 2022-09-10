package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    ProductService productService;
    ProducerService producerService;

    @Autowired
    public ModeratorController(ProductService productService, ProducerService producerService) {
        this.productService = productService;
        this.producerService = producerService;
    }

    @GetMapping("/product_registration")
    public ModelAndView productRegistrationPage(@ModelAttribute("product") Product product) {
        ModelAndView view = new ModelAndView();

        List<ProductType> listOfTypes = new ArrayList<>(Arrays.asList(ProductType.values()));

        List<Producer> listOfProducer = producerService.findAll();

        view.addObject("listOfProducts", listOfTypes);
        view.addObject("listOfProducers", listOfProducer);
        view.setViewName("moderator/productRegistration");
        return view;
    }

    @PostMapping("/product_registration")
    public ModelAndView productRegistration(@ModelAttribute("product") Product product) {
        ModelAndView view = new ModelAndView();
        productService.add(product);
        view.addObject(product);
        view.setViewName("redirect://shopMenu");
        return view;//
    }

    @GetMapping("/terms")
    public ModelAndView termsPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("moderator/termsOfService");
        return view;
    }

}
