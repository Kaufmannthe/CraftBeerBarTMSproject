package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.service.ProductService;
import com.example.craftbeerbartmsproject.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopServiceImpl implements ShopService {

    private final ProductService productService;

    @Autowired
    public ShopServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> randomUniqueProductsList() {
        Random random = new Random();
        List<Product> resultList = new ArrayList<>();
        Set<Long> set = new LinkedHashSet<>();
        if (!productService.findAll().isEmpty()) {
            long originNumber = (int) Objects.requireNonNull(
                    productService.findAll().stream().findFirst().orElse(null)).getId();
            long boundNumber = productService.findAll().get(productService.findAll().size() - 1).getId();

            if (productService.findAll().size() >= 4) {
                while (set.size() < 4) {
                    long randomId = random.nextLong(originNumber, boundNumber + 1);
                    if (productService.findById(randomId) != null) {
                        set.add(randomId);
                    }
                }
            } else {
                while (set.size() < productService.findAll().size()) {
                    long randomId = random.nextLong(originNumber, boundNumber + 1);
                    if (productService.findById(randomId) != null) {
                        set.add(randomId);
                    }
                }
            }
            for (long i : set) {
                resultList.add(productService.findById(i));
            }
        }
        return resultList;
    }

    @Override
    public List<Product> sortProductsByName(String name) {
        List<Product> productList = new ArrayList<>();
        for (Product p : productService.findAll()) {
            if (p.getType().name().toLowerCase().equals(name)) {
                productList.add(p);
            }
        }
        return productList;
    }
}
