package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.Rating;
import com.example.craftbeerbartmsproject.repository.RatingRepository;
import com.example.craftbeerbartmsproject.service.RatingService;
import com.example.craftbeerbartmsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserService userService;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, UserService userService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
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
    public Rating add(Rating rating, Authentication authentication, Product productId) {
        return ratingRepository.save(new Rating(rating.getRating(), productId, userService.getAuthUser(authentication)));
    }

}
