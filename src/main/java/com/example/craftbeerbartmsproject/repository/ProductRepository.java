package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

}
