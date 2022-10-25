package com.example.craftbeerbartmsproject.service.impl;

import com.example.craftbeerbartmsproject.model.Product;
import com.example.craftbeerbartmsproject.model.Rating;
import com.example.craftbeerbartmsproject.model.User;
import com.example.craftbeerbartmsproject.repository.RatingRepository;
import com.example.craftbeerbartmsproject.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating update(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating add(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating findRatingByUserAndProduct(User user, Product product) {
        if (ratingRepository.findRatingByUserAndProduct(user,product) == null){
            return null;
        }
        return ratingRepository.findRatingByUserAndProduct(user, product);
    }

    @Override
    public List<Rating> findAllByProduct(Product product) {
        return ratingRepository.findAllByProduct(product);
    }

    @Override
    public double averageRating(List<Rating> ratings) {
        double rating = 0;
        double avRating;
        for (Rating r : ratings) {
            rating += r.getRating();
        }
        avRating = rating / ratings.size();
        return avRating;
    }

}
