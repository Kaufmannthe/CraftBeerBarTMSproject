package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProducerService {

    List<Producer> findAll();

    Optional<Producer> findById(Long id);

    Producer findByName(String name);

    Producer update(Producer producer);

    Producer add(Producer producer);

    void delete(Producer producer);
}
