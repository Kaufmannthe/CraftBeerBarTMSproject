package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.Rating;
import com.example.craftbeerbartmsproject.service.RatingService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rating")
@PreAuthorize("hasAuthority('USER')")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ModelAndView newMark(Authentication authentication, @ModelAttribute("mark") Rating rating,
                                @ModelAttribute("product") Product product) {
        ModelAndView view = new ModelAndView();
        ratingService.add(rating, authentication, product);
        view.setViewName("redirect:/shop/product/" + product.getId());
        return view;
    }


}
