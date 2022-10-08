package com.example.craftbeerbartmsproject.service;

import com.example.craftbeerbartmsproject.model.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> findAllByUserId(long userId);

    Rating update(Rating rating);

    Rating add(Rating rating);
}
