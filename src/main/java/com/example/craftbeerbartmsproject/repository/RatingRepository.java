package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.Rating;
import com.example.craftbeerbartmsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAll();

    List<Rating> findAllByProduct(Product product);

    Rating findRatingByUserAndProduct(User user, Product product);

}
