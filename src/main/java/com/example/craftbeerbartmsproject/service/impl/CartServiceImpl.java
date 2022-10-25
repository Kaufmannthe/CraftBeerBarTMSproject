package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.CartRepository;
import com.example.craftbeerbartmsproject.service.CartService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    public void add(Product product, User user) {
        List<Cart> cartList = findCartsByUsername(user);
        List<Product> productList = new ArrayList<>();
        for (Cart cart : cartList) {
            productList.add(cart.getProduct());
        }
        if (!productList.contains(product)) {
            cartRepository.save(new Cart(user.getId(), product));
        }
    }

    public List<Cart> all(long userId) {
        return cartRepository.findCartByUserId(userId);
    }

    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public List<Cart> findCartsByUsername(User authUser) {
        return new ArrayList<>(all(authUser.getId()));
    }

    @Override
    public void deleteCartByUsernameAndProductId(Authentication authentication, long product_id) {
        List<Cart> cartList = findCartsByUsername(userService.getAuthUser(authentication));
        for (Cart cart : cartList) {
            if (cart.getProduct().getId() == product_id) {
                delete(cart);
            }
        }
    }
}
