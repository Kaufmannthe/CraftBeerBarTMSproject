package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.Order;
import com.example.craftbeerbartmsproject.model.Producer;
import com.example.craftbeerbartmsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User userId);
    List<Order> findAllByProducer(Producer producerId);
    Order findById(long id);

}
