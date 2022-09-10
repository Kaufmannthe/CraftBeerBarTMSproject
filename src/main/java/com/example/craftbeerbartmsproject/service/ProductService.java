package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<Product> findAllByType(ProductType type);

    Product findById(long id);

    Product findByName(String name);

    Product update(Product product);

    Product add(Product product);

    void delete(Product product);
}
