package com.FSD.ITS.services;

import com.FSD.ITS.daos.UserRepository;
import com.FSD.ITS.entities.Interview;
import com.FSD.ITS.entities.User;
import com.FSD.ITS.exceptions.InvalidData;
import com.FSD.ITS.exceptions.NotFoundException;
import com.FSD.ITS.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    InterviewService interviewService;

    @PersistenceContext
    private EntityManager entityManger;

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
            if (null == user.getInterviews()) {
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
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidData("User Id : " + userId + " not found."));
        Set<Interview> interviews = user.getInterviews();
        interviews.forEach((interview) -> interviewService.removeUsersFromInterview(interview.getInterviewId(), userId));
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        UserValidator userValidator = new UserValidator();
        if (userValidator.validateUser(user)) {
            User curUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new NotFoundException("User", "User Id", user.getUserId()));
            curUser.setEmail(user.getEmail());
            curUser.setFname(user.getFname());
            curUser.setlName(user.getlName());
            curUser.setMobile(user.getMobile());
            if (null != user.getInterviews()) {
                user.getInterviews().forEach(interview -> {
                    interviewService.addUsersToInterview(interview.getInterviewId(), user.getUserId());
                });
            }
            userRepository.save(curUser);
            return user;
        } else
            throw new InvalidData(userValidator.getErrors());
    }
}
