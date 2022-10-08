package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.service.CartService;
import com.example.craftbeerbartmsproject.service.OrderService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;

    public OrderController(OrderService orderService, UserService userService, CartService cartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @PostMapping
    public ModelAndView newOrder(@ModelAttribute("order") Order order, @ModelAttribute("cart") Cart cart,
                                 Authentication authentication) {
        ModelAndView view = new ModelAndView();
        orderService.add(order, authentication);
        cartService.findCartByUsernameAndProductId(authentication, order.getProduct().getId());
        view.setViewName("redirect:/shop");
        return view;
    }
}
