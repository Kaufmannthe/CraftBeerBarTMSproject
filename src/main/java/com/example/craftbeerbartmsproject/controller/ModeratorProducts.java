package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ModeratorProducts {
    private final ProductService productService;
    private final ProducerService producerService;

    @Autowired
    public ModeratorProducts(ProductService productService, ProducerService producerService) {
        this.productService = productService;
        this.producerService = producerService;
    }

    @GetMapping("/all_products")
    public ModelAndView allProducts(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("products", productService.findProductsByProducer(authentication));
        view.setViewName("/moderator/all_products");
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView productPage(@PathVariable(name = "id") Product product, Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.addObject("product", product);
        view.addObject("products", productService.findProductsByProducer(authentication));
        view.setViewName("moderator/product");
        return view;
    }
}
