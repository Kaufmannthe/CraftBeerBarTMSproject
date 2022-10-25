package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.Rating;
import com.example.craftbeerbartmsproject.model.User;

import java.util.List;

public interface RatingService {

    Rating update(Rating rating);

    Rating add(Rating rating);

    Rating findRatingByUserAndProduct(User user, Product product);

    List<Rating> findAllByProduct(Product product);

    double averageRating(List<Rating> ratings);

}
