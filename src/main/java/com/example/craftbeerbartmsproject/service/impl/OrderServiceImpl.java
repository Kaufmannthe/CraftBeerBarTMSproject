package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Cart;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.OrderRepository;
import com.example.craftbeerbartmsproject.service.CartService;
import com.example.craftbeerbartmsproject.service.OrderService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public List<Order> findAllByUserId(User userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public List<Order> findAllByProducer(Producer producer) {
        List<Order> orderList = orderRepository.findAll();
        List<Order> producerOrders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getProducer() == producer) {
                producerOrders.add(order);
            }
        }
        return producerOrders;
    }

    @Override
    public void add(Order order, Authentication authentication) {
        order.setUser(userService.getAuthUser(authentication));
        orderRepository.save(order);
    }


    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

}
