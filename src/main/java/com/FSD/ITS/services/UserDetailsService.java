package com.FSD.ITS.services;

import com.FSD.ITS.entities.User;
import com.FSD.ITS.exceptions.NotFoundException;

import java.util.List;

public interface UserDetailsService {
    List<User> getAllUsers();
    User getUserById(int userId) throws NotFoundException;
    User addUser(User user);
    void deleteUser(int userId);
}
