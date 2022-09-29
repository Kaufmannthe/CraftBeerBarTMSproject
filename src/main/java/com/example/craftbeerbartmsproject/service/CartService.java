package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.User;

import java.util.List;

public interface CartService {

    void add(Product product, User user);

    List<Cart> all(long userId);

    void delete(Cart cart);

    List<Cart> findCartsByUsername(User authUser);



}
