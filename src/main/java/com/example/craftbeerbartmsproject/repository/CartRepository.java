package com.example.craftbeerbartmsproject.repository;


import com.example.craftbeerbartmsproject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findCartByUserId(long id);



}
