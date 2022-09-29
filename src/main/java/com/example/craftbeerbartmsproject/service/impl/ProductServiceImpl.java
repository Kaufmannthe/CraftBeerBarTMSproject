package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.ProductRepository;
import com.example.craftbeerbartmsproject.service.CartService;
import com.example.craftbeerbartmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findAllByType(ProductType type) {
        return repository.findAllByType(type);
    }

    @Override
    public Product findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Product findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }

    @Override
    public Product add(Product product) {
        product.setDataCreated(LocalDate.now());
        repository.save(product);
        repository.flush();
        return product;
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
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
}
