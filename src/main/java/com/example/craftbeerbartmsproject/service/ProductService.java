package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    List<Product> findAllByType(ProductType type);

    Optional<Product> findById(Long id);

    Product findByName(String name);

    Product update(Product product);

    Product add(Product product);

    void delete(Product product);
}
