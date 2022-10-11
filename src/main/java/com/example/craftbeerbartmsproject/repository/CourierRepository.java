package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier,Long> {

    Courier findByProducerId(long id);

}
