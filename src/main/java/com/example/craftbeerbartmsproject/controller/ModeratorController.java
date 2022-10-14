package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Courier;
import com.example.craftbeerbartmsproject.service.CourierService;
import com.example.craftbeerbartmsproject.service.OrderService;
import com.example.craftbeerbartmsproject.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/moderator")
@PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMIN')")
public class ModeratorController {
    private final ProducerService producerService;
    private final OrderService orderService;

    private final CourierService courierService;

    @Autowired
    public ModeratorController(ProducerService producerService, OrderService orderService, CourierService courierService) {
        this.producerService = producerService;
        this.orderService = orderService;
        this.courierService = courierService;
    }

    @GetMapping("/profile")
    public ModelAndView producerProfilePage(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.setViewName("/moderator/profile");
        return view;
    }

    @GetMapping("/orders")
    public ModelAndView moderatorOrders(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("orders", orderService.findAllByProducer
                (producerService.findByLogin(authentication.getName())));
        view.addObject("status", orderService.getStatuses());
        view.setViewName("moderator/orders");
        return view;
    }

    @GetMapping("/couriers")
    public ModelAndView couriers(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("couriers",
                courierService.findByProducerId(producerService.findByLogin(authentication.getName()).getId()));
        view.setViewName("moderator/couriers");
        return view;
    }

    @GetMapping("/new_courier")
    public ModelAndView createCourierPage(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("producer", producerService.findByLogin(authentication.getName()));
        view.addObject("courier", new Courier());
        view.setViewName("moderator/courierRegistration");
        return view;
    }

    @PostMapping("/new_courier")
    public ModelAndView createCourier(@ModelAttribute("courier") Courier courier) {
        ModelAndView view = new ModelAndView();
        courierService.add(courier);
        view.setViewName("redirect:/moderator/couriers");
        return view;
    }
}
