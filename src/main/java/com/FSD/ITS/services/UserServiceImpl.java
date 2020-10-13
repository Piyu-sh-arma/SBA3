package com.FSD.ITS.services;

import com.FSD.ITS.daos.InterviewRepository;
import com.FSD.ITS.daos.UserRepository;
import com.FSD.ITS.entities.Interview;
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
    UserRepository userRepository;

    @Autowired
    InterviewRepository interviewRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(() -> new InvalidData("User Id" + userId + " not found."));
    }

    @Override
    @Transactional
    public User addUser(User user) {
        UserValidator userValidator = new UserValidator();
        if (userValidator.validateUser(user)) {
            if (null != user.getInterviews()) {
                Optional<User> usr = userRepository.findById(user.getUserId());
                if (usr.isPresent()) {
                    throw new InvalidData("User Id : " + user.getUserId() + " is already present");
                } else
                    return userRepository.save(user);
            } else
                throw new InvalidData("Can't select users while creating new user.");
        } else
            throw new InvalidData(userValidator.getErrors());
    }

    @Transactional
    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new InvalidData("User Id : "+userId+" not found."));
        user.getInterviews().clear();
        userRepository.save(user);
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        UserValidator userValidator = new UserValidator();
        if (userValidator.validateUser(user)) {
            userRepository.findById(user.getUserId()).orElseThrow(() -> new NotFoundException("User", "User Id", user.getUserId()));
            return userRepository.save(user);
        } else
            throw new InvalidData(userValidator.getErrors());
    }
}
