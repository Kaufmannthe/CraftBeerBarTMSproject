package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.repository.CartRepository;
import com.example.craftbeerbartmsproject.service.CartService;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    CartRepository repository;


    @Autowired
    public CartServiceImpl(CartRepository repository) {
        this.repository = repository;
    }


    @Override
    public void addProduct(Product product) {

    }

    @Override
    public Cart findById(long id) {
        return repository.findById(id);
    }
}
