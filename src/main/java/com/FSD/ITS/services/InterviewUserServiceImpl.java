package com.FSD.ITS.services;

import com.FSD.ITS.daos.InterviewUserRepository;
import com.FSD.ITS.entities.InterviewUser;
import com.FSD.ITS.entities.InterviewUserKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InterviewUserServiceImpl implements InterviewUserService {
    @Autowired
    InterviewUserRepository repository;

    @Override
    public void deleteById(InterviewUserKey id) {
        repository.deleteById(id);

    }

    @Override
    public void deleteInBatch(Set<InterviewUser> interviewUsers) {
        repository.deleteInBatch(interviewUsers);
    }

    @Override
    public void deleteByUserId(int userId) {
        List<InterviewUser> interviewUsers = repository.findAll().stream().filter(interviewUser -> interviewUser.getUser().getUserId() == userId).collect(Collectors.toList());
        repository.deleteInBatch(interviewUsers);
    }
}
