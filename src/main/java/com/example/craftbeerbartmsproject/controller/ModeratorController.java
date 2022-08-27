package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;
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
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    ProductService service;

    @Autowired
    public ModeratorController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/product_registration")
    public ModelAndView productRegistrationPage(@ModelAttribute ("product")Product product){
        ModelAndView view = new ModelAndView();

        List<ProductType> listOfTypes = new ArrayList<>(Arrays.asList(ProductType.values()));

        view.addObject("list", listOfTypes);
        view.setViewName("moderator/productRegistration");
        return view;
    }
    @PostMapping("/product_registration")
    public ModelAndView productRegistration(@ModelAttribute ("product")Product product){
        ModelAndView view = new ModelAndView();
        service.add(product);
        view.addObject(product);
        view.setViewName("redirect://shop");
        return view;//
    }

}
