package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Courier;
import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.OrderStatus;
import com.example.craftbeerbartmsproject.repository.CourierRepository;
import com.example.craftbeerbartmsproject.repository.OrderRepository;
import com.example.craftbeerbartmsproject.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierServiceImpl implements CourierService {

    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CourierServiceImpl(CourierRepository courierRepository, OrderRepository orderRepository,
                              PasswordEncoder passwordEncoder) {
        this.courierRepository = courierRepository;
        this.orderRepository = orderRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Courier> findByProducerId(long id) {
        return courierRepository.findByProducerId(id);
    }


    @Override
    public Courier add(Courier courier) {
        courier.setPassword(passwordEncoder.encode(courier.getPassword()));
        return courierRepository.save(courier);
    }

    @Override
    public Courier findCourierByLogin(String login) {
        return courierRepository.findCourierByLogin(login);
    }

    @Override
    public void takeInDelivery(Order order, Authentication authentication) {
        order.setCourier(courierRepository.findCourierByLogin(authentication.getName()));
        order.setOrderStatus(OrderStatus.COURIER_DELIVERING);
        orderRepository.save(order);
    }


}
