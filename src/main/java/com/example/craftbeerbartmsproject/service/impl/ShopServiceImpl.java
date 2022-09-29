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

        List<Product> listOfProducts = productService.findAll();    //list and int to find boundNumber
        int productCount = productService.findAll().size();

        long originNumber = (int) productService.findAll().stream().findFirst().get().getId();
        long boundNumber = listOfProducts.get(productCount - 1).getId();


        if (productService.findAll().size() >= 4) {
            while (set.size() < 4) {
                long randomId = random.nextLong(originNumber, boundNumber + 1);
                if (productService.findById(randomId).getDataCreated() != null) {
                    set.add(randomId);
                }
            }
        } else {
            while (set.size() < productService.findAll().size()) {
                long randomId = random.nextLong(originNumber, boundNumber + 1);
                if (productService.findById(randomId).getDataCreated() != null) {
                    set.add(randomId);
                }
            }
        }

        for (long i : set) {
            resultList.add(productService.findById(i));
        }
        return resultList;
    }

    @Override
    public long minimalIndex() {
        return (int) productService.findAll().stream().findFirst().get().getId();
    }

    @Override
    public long maximumIndex() {
        return productService.findAll().get(productService.findAll().size() - 1).getId();
    }

    @Override
    public List<Product> sortProductsByName(String name) {
        List<Product> productList = new ArrayList<>();
        List<Product> allProducts = productService.findAll();

        for (Product p : allProducts) {
            if (p.getType().name().toLowerCase().equals(name)) {
                productList.add(p);
            }
        }
        return productList;
    }


}
