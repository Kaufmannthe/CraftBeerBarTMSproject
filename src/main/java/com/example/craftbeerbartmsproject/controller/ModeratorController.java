package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.service.OrderService;
import com.example.craftbeerbartmsproject.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/moderator")
@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
public class ModeratorController {
    private final ProducerService producerService;
    private final OrderService orderService;

    @Autowired
    public ModeratorController(ProducerService producerService, OrderService orderService) {
        this.producerService = producerService;
        this.orderService = orderService;
    }

    @GetMapping("/profile")
    public ModelAndView producerProfilePage(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.setViewName("/moderator/profile");
        return view;
    }
    @GetMapping("/orders")
    @PreAuthorize("hasAnyAuthority('MODERATOR')")
    public ModelAndView moderatorOrders(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("orders", orderService.findAllByProducer
                (producerService.findByLogin(authentication.getName())));
        view.addObject("status", orderService.getStatuses());
        view.setViewName("moderator/orders");
        return view;
    }

}
