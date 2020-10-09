package com.FSD.ITS.services;

import com.FSD.ITS.entities.User;

import java.util.List;

public interface UserDetails {
    List<User> getAllUsers();
    User getUserById(int userId);
    User addUser(User user);
    void deleteUser(int userId);
}
