package com.FSD.ITS.services;

import com.FSD.ITS.entities.User;
import com.FSD.ITS.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int userId) throws NotFoundException;
    User addUser(User user);
    void deleteUser(int userId);
    User saveUser(User user) throws NotFoundException;
}
