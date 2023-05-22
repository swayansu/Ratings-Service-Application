package com.userservice.userservice.services;

import com.userservice.userservice.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    List<User> getAlluser();

    User getUser(String userId);

    User updateUser(User user, String userId);

    void deleteUser(String userId);

}
