package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Courier;
import com.example.craftbeerbartmsproject.model.Order;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CourierService {

    List<Courier> findByProducerId(long id);

    Courier add(Courier courier);

    Courier findCourierByLogin(String login);

    void takeInDelivery(Order order, Authentication authentication);
}
