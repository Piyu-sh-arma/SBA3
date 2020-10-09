package com.FSD.ITS.controllers;

import com.FSD.ITS.entities.User;
import com.FSD.ITS.exceptions.NotFoundException;
import com.FSD.ITS.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserDetailsService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(value = "id") int userId) throws NotFoundException {
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable(value = "id") int userId){
        userService.deleteUser(userId);
    }








}
