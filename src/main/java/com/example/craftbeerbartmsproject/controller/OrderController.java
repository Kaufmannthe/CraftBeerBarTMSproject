package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.OrderStatus;
import com.example.craftbeerbartmsproject.service.CartService;
import com.example.craftbeerbartmsproject.service.OrderService;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/order")
@PreAuthorize("hasAuthority('USER')")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;
    private final ProducerService producerService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService, UserService userService, ProducerService producerService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
        this.producerService = producerService;
    }

    @PostMapping
    public ModelAndView newOrder(@ModelAttribute("order") Order order, @ModelAttribute("cart") Cart cart,
                                 Authentication authentication) {
        ModelAndView view = new ModelAndView();
        orderService.add(order, authentication);
        cartService.deleteCartByUsernameAndProductId(authentication, order.getProduct().getId());
        view.setViewName("redirect:/cart");
        return view;
    }

    @GetMapping
    public ModelAndView userOrders(Authentication authentication) {
        ModelAndView view = new ModelAndView();
        view.addObject("orders", orderService.findAllByUser(userService.getAuthUser(authentication)));
        view.setViewName("user/orders");
        return view;
    }

    @PostMapping("/ready/{id}")
    @PreAuthorize("hasAuthority('MODERATOR')")
    public ModelAndView statusReady(@PathVariable ("id") Order order) {
        ModelAndView view = new ModelAndView();
        orderService.statusUpdate(order.getId(), OrderStatus.PRODUCER_CONFIRM);
        view.setViewName("redirect:/moderator/orders");
        return view;
    }

}
