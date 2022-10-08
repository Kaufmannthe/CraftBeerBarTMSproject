package com.example.craftbeerbartmsproject.repository;

import com.example.craftbeerbartmsproject.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    List<Rating> findAllByUserId(long user_id);
    List<Rating> findAll();


}
