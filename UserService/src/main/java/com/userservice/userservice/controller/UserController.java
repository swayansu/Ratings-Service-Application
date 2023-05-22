package com.userservice.userservice.controller;

import com.userservice.userservice.entities.User;
import com.userservice.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user){
       User user1 =  userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    ResponseEntity<User> getSingleUser(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @GetMapping
    ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAlluser());
    }

    @PutMapping("/{userId}")
    ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String userId){
        return ResponseEntity.ok(userService.updateUser(user, userId));
    }

}
