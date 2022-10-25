package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.service.CourierService;
import com.example.craftbeerbartmsproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/courier")
@PreAuthorize("hasAuthority('COURIER')")
public class CourierController {
    private final CourierService courierService;
    private final OrderService orderService;

    @Autowired
    public CourierController(CourierService courierService, OrderService orderService) {
        this.courierService = courierService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ModelAndView allOrders(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("orders",
                orderService.ordersForCourier(courierService.findCourierByLogin(authentication.getName())));
        view.addObject("courier", courierService.findCourierByLogin(authentication.getName()));
        view.setViewName("/courier/orders");
        return view;
    }

}
