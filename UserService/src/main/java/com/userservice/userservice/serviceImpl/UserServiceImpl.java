package com.userservice.userservice.serviceImpl;

import com.userservice.userservice.Exception.ResourceNotFoundException;
import com.userservice.userservice.entities.Hotel;
import com.userservice.userservice.entities.Rating;
import com.userservice.userservice.entities.User;
import com.userservice.userservice.external.services.HotelService;
import com.userservice.userservice.external.services.RatingService;
import com.userservice.userservice.repositories.UserRepository;
import com.userservice.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.Response;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger  = LoggerFactory.getLogger(UserService.class);

    private List<Rating> getUserRating(List<Rating> users){
        List<Rating> ratingList = users.stream().map(rating -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        return ratingList;
    }

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAlluser() {
        List<User> updatedUserList= userRepository.findAll();
        return updatedUserList.stream().map(user -> {
            List<Rating> userRatings = ratingService.getRatingsByUserId(user.getUserId());
            List<Rating> ratingList = getUserRating(userRatings);
            user.setRatings(ratingList);
            return user;
        }).collect(Collectors.toList());
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);


        // Fetching the getRatingsByUser() from the ratingServices using Resttemplate
        // Url: http://localhost:8083/ratings/{userID}

//        Rating[] forObject = restTemplate.getForObject("http://RATINGS-SERVICE/ratings/"+userId, Rating[].class);
//
//        //Adding The hotels by calling hotels by ID
//        List<Rating> ratings = Arrays.asList(forObject);
//        List<Rating> ratingList = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            rating.setHotel(hotel);
//            return rating;
//        }).collect(Collectors.toList());
//        user.setRatings(ratingList);
//        return user;
        List<Rating> userRatings = ratingService.getRatingsByUserId(userId);
        List<Rating> ratingList = getUserRating(userRatings);
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(User user, String userId) {
        User user1 = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with User ID "+userId+"Not found"));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user1 = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with User ID "+userId+"Not found"));
        userRepository.deleteById(userId);
    }
}
