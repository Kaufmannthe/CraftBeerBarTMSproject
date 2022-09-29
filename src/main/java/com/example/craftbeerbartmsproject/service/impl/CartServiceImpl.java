package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.CartRepository;
import com.example.craftbeerbartmsproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void add(Product product, User user) {
        Cart cart = new Cart(user.getId(), product);
        cartRepository.save(cart);
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
}
