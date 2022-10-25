package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(long id);

    Product update(Product product);

    Product add(Product product, MultipartFile file) throws IOException;

    void delete(Product product);

    String saveImage(MultipartFile file) throws IOException;

    List<Product> findProductsByCarts(List<Cart> carts);

    List<Product> findProductsByProducer(Authentication authentication);

    List<Product> sortedByRatingList();

    List<Product>sortedByDataCreated();
}
