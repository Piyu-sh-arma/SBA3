package com.FSD.ITS.services;

import com.FSD.ITS.daos.UserRepository;
import com.FSD.ITS.entities.User;
import com.FSD.ITS.exceptions.InvalidData;
import com.FSD.ITS.exceptions.NotFoundException;
import com.FSD.ITS.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    InterviewUserService interviewUserService;

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return repository.findById(userId).orElseThrow(() -> new InvalidData("User Id" + userId + " not found."));
    }

    @Override
    @Transactional
    public User addUser(User user) {
        UserValidator userValidator = new UserValidator();
        if (userValidator.validateUser(user)) {
            Optional<User> usr = repository.findById(user.getUserId());
            if (usr.isPresent()) {
                throw new InvalidData(user.getUserId() + " is already present");
            } else {
                return repository.save(user);
            }
        } else
            throw new InvalidData(userValidator.getErrors());
    }

    @Transactional
    @Override
    public void deleteUser(int userId) {
        if (repository.findById(userId).isPresent()) {
            interviewUserService.deleteByUserId(userId);
            repository.deleteById(userId);
        } else
            throw new InvalidData("User Id : " + userId + " is invalid");
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        UserValidator userValidator = new UserValidator();
        if (userValidator.validateUser(user)) {
            repository.findById(user.getUserId()).orElseThrow(() -> new NotFoundException("User", "User Id", user.getUserId()));
            return repository.save(user);
        } else
            throw new InvalidData(userValidator.getErrors());
    }
}
