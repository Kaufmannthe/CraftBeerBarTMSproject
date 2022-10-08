package com.example.craftbeerbartmsproject.controller;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.service.CartService;
import com.example.craftbeerbartmsproject.service.ProductService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cart")
@PreAuthorize("hasAuthority('USER')")
public class CartController {

    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;

    @Autowired
    public CartController(ProductService productService, UserService userService, CartService cartService) {
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping
    public ModelAndView cart(Authentication authentication, @ModelAttribute ("order") Order order) {
        ModelAndView view = new ModelAndView();
        view.addObject("listOfProducts", productService.findProductsByCarts
                (cartService.findCartsByUsername(userService.getAuthUser(authentication))));
        view.addObject("listOfCarts",
                cartService.findCartsByUsername(userService.getAuthUser(authentication)));
        view.setViewName("/user/cart");
        return view;
    }

    @PostMapping
    public ModelAndView addCart(Authentication authentication, @ModelAttribute("product") Product product) {
        ModelAndView view = new ModelAndView();
        cartService.add(product, userService.getAuthUser(authentication));
        view.setViewName("redirect:/shop/product/all");
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView deleteProductFromCart(@PathVariable(name = "id") Cart cart) {
        ModelAndView view = new ModelAndView();
        cartService.delete(cart);
        view.setViewName("redirect:/cart");
        return view;
    }
}
