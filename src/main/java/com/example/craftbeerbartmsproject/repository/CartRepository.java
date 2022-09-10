package com.example.craftbeerbartmsproject.repository;


import com.example.craftbeerbartmsproject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findById(long id);


}
