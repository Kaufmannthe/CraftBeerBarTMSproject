package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<Courier,Long> {

    List<Courier> findByProducerId(long id);

    Courier findCourierByLogin(String login);

}
