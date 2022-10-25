package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.*;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    List<Order> findAllByUser(User userId);

    List<Order> findAllByProducer(Producer producer);

    void add(Order order, Authentication authentication);

    void delete(Order order);

    void statusUpdate(long orderId, OrderStatus orderStatus);

    List<OrderStatus> getStatuses();

    void problemCheck(Order order);

    void deliveredAndPaidByUserCheck (Order order);

    void deliveredCheck (Order order);

    List<Order> sortOrdersByStatus(String name);

    int valueOfNewOrders(Authentication authentication);

    int valueOfProblemOrders(Authentication authentication);

    List<Order> ordersForCourier(Courier courier);

    List<Order> deliveredOrders(Authentication authentication);
}
