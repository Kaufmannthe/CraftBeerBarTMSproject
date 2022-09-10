package com.example.craftbeerbartmsproject.service;


import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Product;

public interface CartService {

    void addProduct(Product product);

    Cart findById(long id);


}
