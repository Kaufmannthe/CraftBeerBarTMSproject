package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.ProductService;
import com.example.craftbeerbartmsproject.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
@PreAuthorize("hasAuthority('MODERATOR')")
public class ModeratorProducts {
    private final ProductService productService;
    private final ProducerService producerService;
    private final RatingService ratingService;

    @Autowired
    public ModeratorProducts(ProductService productService, ProducerService producerService, RatingService ratingService) {
        this.productService = productService;
        this.producerService = producerService;
        this.ratingService = ratingService;
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
        view.addObject("averageRating", ratingService.averageRating(ratingService.findAllByProduct(product)));
        view.setViewName("moderator/product");
        return view;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable(name = "id") Product product) {
        ModelAndView view = new ModelAndView();
        productService.delete(product);
        view.setViewName("redirect:/products/all_products");
        return view;
    }
}
