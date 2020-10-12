package com.FSD.ITS.services;

import com.FSD.ITS.entities.InterviewUser;
import com.FSD.ITS.entities.InterviewUserKey;

import java.util.Set;

public interface InterviewUserService {
    void deleteById(InterviewUserKey interviewUserKey);
    void deleteInBatch(Set<InterviewUser> interviewUsers);
    void deleteByUserId(int userId);
}
