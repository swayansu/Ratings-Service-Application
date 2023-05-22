package com.microservices.ratingservices.repositories;

import com.microservices.ratingservices.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingsRepo extends MongoRepository<Rating, String> {


    List<Rating> findByUserId(String hotelId);
    List<Rating> findByHotelId(String hotelId);
}
