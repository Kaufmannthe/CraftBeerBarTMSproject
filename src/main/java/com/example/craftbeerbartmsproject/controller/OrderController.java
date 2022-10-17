package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.OrderStatus;
import com.example.craftbeerbartmsproject.repository.OrderRepository;
import com.example.craftbeerbartmsproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final CourierService courierService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService,
                           UserService userService, OrderRepository orderRepository, CourierService courierService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.courierService = courierService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView newOrder(@ModelAttribute("order") Order order, @ModelAttribute("cart") Cart cart,
                                 Authentication authentication) {
        ModelAndView view = new ModelAndView();
        orderService.add(order, authentication);
        cartService.deleteCartByUsernameAndProductId(authentication, order.getProduct().getId());
        view.setViewName("redirect:/cart");
        return view;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView userOrders(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("orders", orderService.findAllByUser(userService.getAuthUser(authentication)));
        view.setViewName("user/orders");
        return view;
    }

    @PostMapping("/ready/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView statusReady(@PathVariable("id") Order order) {
        ModelAndView view = new ModelAndView();
        orderService.statusUpdate(order.getId(), OrderStatus.PRODUCER_CONFIRM);
        view.setViewName("redirect:/moderator/orders");
        return view;
    }

    @PostMapping("/delivering/{id}")
    @PreAuthorize("hasAuthority('COURIER')")
    public ModelAndView changeStatusToDelivering(@PathVariable("id") Order order, Authentication authentication) {
        ModelAndView view = new ModelAndView();
        courierService.takeInDelivery(order, authentication);
        view.setViewName("redirect:/courier/orders");
        return view;
    }

    @PostMapping("/delivered/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView changeStatusToDelivered(@PathVariable("id") Order order) {
        ModelAndView view = new ModelAndView();
        orderService.deliveredCheck(order);
        orderService.problemCheck(order);
        view.setViewName("redirect:/order");
        return view;
    }

    @PostMapping("/deliveredAndPaid/{id}")
    @PreAuthorize("hasAuthority('COURIER')")
    public ModelAndView payConfirm(@PathVariable("id") Order order) {
        ModelAndView view = new ModelAndView();
        orderService.deliveredAndPaidByUserCheck(order);
        orderService.problemCheck(order);
        view.setViewName("redirect:/courier/orders");
        return view;
    }
}
