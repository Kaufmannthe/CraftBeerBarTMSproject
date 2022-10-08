package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Rating;
import com.example.craftbeerbartmsproject.repository.RatingRepository;
import com.example.craftbeerbartmsproject.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> findAllByUserId(long userId) {
        return ratingRepository.findAllByUserId(userId);
    }

    @Override
    public Rating update(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating add(Rating rating) {
        return ratingRepository.save(rating);
    }

}
