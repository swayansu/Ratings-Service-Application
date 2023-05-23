package com.userservice.userservice;

import com.userservice.userservice.entities.Rating;
import com.userservice.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private RatingService ratingService;

    @Test
    void createRating(){
        Rating rating = Rating.builder().rating(7)
                .userId("1d45ae99-a978-40a0-99f6-bc149b75fd28")
                .hotelId("ce416821-010d-4bce-98d8-f094319299dd")
                .feedback("A luxurious hotel with top-notch service.")
                .build();
        System.out.println(ratingService.createRating(rating));
    }

}
