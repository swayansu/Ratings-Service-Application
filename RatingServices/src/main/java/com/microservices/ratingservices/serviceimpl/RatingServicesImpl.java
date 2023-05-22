package com.microservices.ratingservices.serviceimpl;

import com.microservices.ratingservices.Exception.ResourceNotFoundException;
import com.microservices.ratingservices.entities.Rating;
import com.microservices.ratingservices.repositories.RatingsRepo;
import com.microservices.ratingservices.serices.RatingServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServicesImpl implements RatingServices {

    // Dependency Injection
    private final RatingsRepo ratingsRepo;
    RatingServicesImpl(RatingsRepo ratingsRepo){
        this.ratingsRepo = ratingsRepo;
    }

    @Override
    public Rating addRatings(Rating rating) {
        return ratingsRepo.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingsRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingsRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotel(String hotelId) {
        return ratingsRepo.findByHotelId(hotelId);
    }
}
