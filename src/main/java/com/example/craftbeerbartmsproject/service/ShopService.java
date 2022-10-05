package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Product;

import java.util.List;

public interface ShopService {

    List<Product> randomUniqueProductsList();

    List<Product> findProducts(List<Product> productList);

    List<Product> sortProductsByName(String name);
}
