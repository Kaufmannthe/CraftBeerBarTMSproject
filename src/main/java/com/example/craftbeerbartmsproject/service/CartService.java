package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.User;

import java.util.List;

public interface CartService {

    public void add(Product product, User user);

    public List<Cart> all(long userId);

    public void delete(Cart cart);

}
