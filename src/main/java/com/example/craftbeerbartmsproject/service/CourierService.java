package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Courier;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.Producer;

import java.util.List;

public interface CourierService {

    List<Courier> findByProducerId(long id);

    List<Order> findOrdersByCourierId(long id, Producer producer);

    Courier add(Courier courier);

    void changeStatusToDelivered(Order order);

    void isPaid(Order order,boolean status);

    Courier findCourierByLogin(String login);
}
