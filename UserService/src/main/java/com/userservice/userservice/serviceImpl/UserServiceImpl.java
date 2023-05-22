package com.userservice.userservice.serviceImpl;

import com.userservice.userservice.Exception.ResourceNotFoundException;
import com.userservice.userservice.entities.Rating;
import com.userservice.userservice.entities.User;
import com.userservice.userservice.repositories.UserRepository;
import com.userservice.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger  = LoggerFactory.getLogger(UserService.class);


    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return this.userRepository.save(user);
    }

    @Override
    public List<User> getAlluser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        // Fetching the getRatingsByUser() from the ratingServices
        // Url: http://localhost:8083/ratings/c80a0a46-3c49-4e1c-a3b2-e0e75d4154a7

        ArrayList forObject = restTemplate.getForObject("http://localhost:8083/ratings/"+userId, ArrayList.class);
//        logger.info("{}", forObject);
        user.setRatings(forObject);
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
