package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Courier;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.OrderStatus;
import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.repository.CourierRepository;
import com.example.craftbeerbartmsproject.repository.OrderRepository;
import com.example.craftbeerbartmsproject.service.CourierService;
import com.example.craftbeerbartmsproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    @Autowired
    public CourierServiceImpl(CourierRepository courierRepository, OrderService orderService,
                              OrderRepository orderRepository) {
        this.courierRepository = courierRepository;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Courier> findByProducerId(long id) {
        return courierRepository.findByProducerId(id);
    }

    @Override
    public List<Order> findOrdersByCourierId(long id, Producer producer) {
        List<Order> orderList = new ArrayList<>();
        for (Order order : orderRepository.findAllByProducer(producer)) {
            if (order.getCourier().getId() == id) {
                orderList.add(order);
            }
        }
        return orderList;
    }

    @Override
    public void changeStatusToDelivered(Order order) {
        orderService.statusUpdate(order.getId(), OrderStatus.COURIER_DELIVERING);
    }

    @Override
    public void isPaid(Order order, boolean status) {
        orderRepository.findById(order.getId()).setDeliveredAndPaidByUser(status);
    }

    @Override
    public Courier findCourierByLogin(String login) {
        return courierRepository.findCourierByLogin(login);
    }
}
