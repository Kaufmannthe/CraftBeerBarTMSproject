package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CartService {

    void add(Product product, User user);

    List<Cart> all(long userId);

    void delete(Cart cart);

    List<Cart> findCartsByUsername(User authUser);

    void findCartByUsernameAndProductId(Authentication authentication, long product_id);

}
