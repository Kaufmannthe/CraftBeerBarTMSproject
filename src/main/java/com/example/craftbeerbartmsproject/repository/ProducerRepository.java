package com.example.craftbeerbartmsproject.repository;


import com.example.craftbeerbartmsproject.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Producer findByLogin(String login);
}
