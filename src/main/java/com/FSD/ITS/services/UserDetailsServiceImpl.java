package com.FSD.ITS.services;

import com.FSD.ITS.daos.UserDetailsRepository;
import com.FSD.ITS.entities.User;
import com.FSD.ITS.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDetailsRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(int userId) throws NotFoundException {
        return repository.findById(userId).orElseThrow(()->new NotFoundException("User","User Id",userId));
    }

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(int userId) {
        repository.deleteById(userId);
    }
}
