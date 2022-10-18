package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.*;
import com.example.craftbeerbartmsproject.repository.OrderRepository;
import com.example.craftbeerbartmsproject.service.CourierService;
import com.example.craftbeerbartmsproject.service.OrderService;
import com.example.craftbeerbartmsproject.service.ProducerService;
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
    private final ProducerService producerService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService,
                            ProducerService producerService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.producerService = producerService;
    }

    @Override
    public List<Order> findAllByUser(User userId) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orderRepository.findAllByUser(userId)) {
            if (order.getOrderStatus() != OrderStatus.DELIVERED) {
                userOrders.add(order);
            }
        }
        return userOrders;
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

    public void problemCheck(Order order) {
        if (order.isDeliveredAndPaidByUser() && order.isReceivedByUser()) {
            order.setOrderStatus(OrderStatus.DELIVERED);
            orderRepository.save(order);
        } else {
            order.setOrderStatus(OrderStatus.PROBLEMS);
            orderRepository.save(order);
        }
    }

    @Override
    public void deliveredAndPaidByUserCheck(Order order) {
        order.setDeliveredAndPaidByUser(true);
        orderRepository.save(order);
    }

    @Override
    public void deliveredCheck(Order order) {
        order.setReceivedByUser(true);
        orderRepository.save(order);
    }

    @Override
    public List<Order> sortOrdersByStatus(String name) {
        List<Order> orderList = new ArrayList<>();
        for (Order o : orderRepository.findAll()) {
            if (o.getOrderStatus().name().toLowerCase().equals(name)) {
                orderList.add(o);
            }
        }
        return orderList;
    }

    @Override
    public int valueOfNewOrders(Authentication authentication) {
        List<Order> list = new ArrayList<>();
        for (Order o : orderRepository.findAllByProducer(producerService.findByLogin(authentication.getName()))) {
            if (o.getOrderStatus() == OrderStatus.NEW) {
                list.add(o);
            }
        }
        return list.size();
    }

    @Override
    public int valueOfProblemOrders(Authentication authentication) {
        List<Order> list = new ArrayList<>();
        for (Order o : orderRepository.findAllByProducer(producerService.findByLogin(authentication.getName()))) {
            if (o.getOrderStatus() == OrderStatus.PROBLEMS) {
                list.add(o);
            }
        }
        return list.size();
    }

    public List<Order> ordersForCourier(Courier courier){
        List<Order> courierOrder = new ArrayList<>();
        for (Order order : findAllByProducer(courier.getProducer())){
            if (order.getCourier() == courier || order.getOrderStatus().name().equals("PRODUCER_CONFIRM")){
                courierOrder.add(order);
            }
        }
        return courierOrder;
    }

}
