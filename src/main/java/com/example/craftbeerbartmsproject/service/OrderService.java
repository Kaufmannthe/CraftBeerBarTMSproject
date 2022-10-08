package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    List<Order> findAllByUserId(User userId);

    List<Order> findAllByProducer(Producer producer);

    void add(Order order, Authentication authentication);

    void delete(Order order);
}
