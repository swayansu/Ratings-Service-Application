package com.microservices.ratingservices.serices;

import com.microservices.ratingservices.entities.Rating;

import java.util.List;

public interface RatingServices {

    Rating addRatings(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingByUserId(String ratingId);

    List<Rating> getRatingsByHotel(String hotelId);
}
