package com.FSD.ITS.controllers;

import com.FSD.ITS.entities.User;
import com.FSD.ITS.services.UserDetails;
import com.FSD.ITS.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserDetails userDetails;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDetails.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(value = "id") int userId){
        return userDetails.getUserById(userId);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userDetails.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable(value = "id") int userId){
        userDetails.deleteUser(userId);
    }








}
