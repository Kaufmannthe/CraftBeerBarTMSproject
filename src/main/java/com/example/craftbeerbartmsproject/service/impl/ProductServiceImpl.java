package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.*;
import com.example.craftbeerbartmsproject.repository.ProducerRepository;
import com.example.craftbeerbartmsproject.repository.ProductRepository;
import com.example.craftbeerbartmsproject.service.CartService;
import com.example.craftbeerbartmsproject.service.ProducerService;
import com.example.craftbeerbartmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProducerRepository producerRepository) {
        this.productRepository = repository;
        this.producerRepository = producerRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByType(ProductType type) {
        return productRepository.findAllByType(type);
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product add(Product product) {
        productRepository.save(product);
        productRepository.flush();
        return product;
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        String folder = "C:\\CraftBeerBarTMSproject\\src\\main\\resources\\static\\img\\uploaded\\product_picture\\";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder + file.getOriginalFilename());
        Files.write(path, bytes);
        return "/img/uploaded/product_picture/" + file.getOriginalFilename();
    }

    @Override
    public List<Product> findProductsByCarts(List<Cart> carts) {
        List<Product> productList = new ArrayList<>();
        for (Cart i : carts) {
            productList.add(i.getProduct());
        }
        return productList;
    }

    @Override
    public List<Product> findProductsByProducer(Authentication authentication) {
        List<Product> productList = new ArrayList<>();
        for (Product product : findAll()) {
            if (product.getProducer() == producerRepository.findByLogin(authentication.getName())) {
                productList.add(product);
            }
        }
        return productList;
    }

}
