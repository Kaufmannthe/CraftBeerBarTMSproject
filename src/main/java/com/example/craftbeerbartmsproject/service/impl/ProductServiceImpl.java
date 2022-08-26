package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;
import com.example.craftbeerbartmsproject.repository.ProductRepository;
import com.example.craftbeerbartmsproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    ProductRepository repository;

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
    public Optional<Product> findById(Long id) {
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
        return repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }
}
