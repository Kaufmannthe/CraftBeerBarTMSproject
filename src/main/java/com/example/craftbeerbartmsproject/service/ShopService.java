package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Product;

import java.util.List;

public interface ShopService {

    List<Product> randomUniqueProductsList();

    List<Product> sortProductsByName(String name);
}
