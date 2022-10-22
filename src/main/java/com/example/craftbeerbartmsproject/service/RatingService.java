package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.Rating;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface RatingService {

    List<Rating> findAllByUserId(long userId);

    Rating update(Rating rating);

    Rating add(Rating rating, Authentication authentication, Product productId);
}
