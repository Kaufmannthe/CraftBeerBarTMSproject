package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.*;
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

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public List<Order> findAllByUser(User userId) {
        return orderRepository.findAllByUser(userId);
    }

    @Override
    public List<Order> findAllByProducer(Producer producer) {
        return orderRepository.findAllByProducer(producer);
    }

    @Override
    public void add(Order order, Authentication authentication) {
        order.setUser(userService.getAuthUser(authentication));
        order.setOrderStatus(OrderStatus.NEW);
        orderRepository.save(order);
    }


    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public void statusUpdate(long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId);
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    public List<OrderStatus> getStatuses() {
        return List.of(OrderStatus.values());
    }

}
