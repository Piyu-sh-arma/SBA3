package com.FSD.ITS.controllers;

import com.FSD.ITS.entities.Interview;
import com.FSD.ITS.entities.User;
import com.FSD.ITS.services.InterviewService;
import com.FSD.ITS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable(value = "id") int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/addInterview/{userId}/{interviewId}")
    public User updateUserAddInterview(@PathVariable(value = "userId") int userId, @PathVariable(value = "interviewId") int interviewId) {
        User user = userService.getUserById(userId);
//        Interview interview = interviewService.findByInterviewId(interviewId);
//        user.getInterviews().add(interview);
//        interview.getUsers().add(user);
        return userService.saveUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable(value = "id") int userId) {
        userService.deleteUser(userId);
    }


}
