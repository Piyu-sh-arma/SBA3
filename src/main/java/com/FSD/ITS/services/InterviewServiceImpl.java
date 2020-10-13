package com.FSD.ITS.services;

import com.FSD.ITS.daos.InterviewRepository;
import com.FSD.ITS.daos.UserRepository;
import com.FSD.ITS.entities.Interview;
import com.FSD.ITS.entities.User;
import com.FSD.ITS.exceptions.InvalidData;
import com.FSD.ITS.validator.InterviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Interview> getInterviews() {
        return interviewRepository.findAll();
    }

    @Override
    public int getInterviewsCount() {
        return interviewRepository.findAll().size();
    }

    @Override
    public Interview findByInterviewId(int interviewId) {
        return interviewRepository.findById(interviewId).orElseThrow(() -> new InvalidData("Interview Id : " + interviewId + " is not found."));
    }

    @Override
    public List<Interview> findAllByInterviewName(String interviewName) {
        return interviewRepository.findByInterviewName(interviewName);
    }

    @Override
    public List<Interview> findAllByInterviewer(String interviewer) {
        return interviewRepository.findAllByInterviewer(interviewer);
    }

    @Override
    @Transactional
    public Interview addInterview(Interview interview) {
        InterviewValidator interviewValidator = new InterviewValidator();
        if (interviewValidator.validateInterview(interview)) {
            if (null != interview.getUsers()) {
                if (interviewRepository.findById(interview.getInterviewId()).isEmpty())
                    return interviewRepository.save(interview);
                else
                    throw new InvalidData("Interview Id is already present.");
            } else
                throw new InvalidData("Can't assign users while creating new interview.");
        } else
            throw new InvalidData(interviewValidator.getErrors());
    }

    @Override
    public void deleteInterview(int interviewId) {
//        Interview interview = entityManager.find(Interview.class, interviewId);
//        if (null != interview)
//            entityManager.remove(interview);
//        else
//            throw new InvalidData("Interview Id is not found.");

        Interview interview = interviewRepository.findById(interviewId).orElseThrow(()->new InvalidData("Interview Id is not found."));
        interview.getUsers().clear();
        interviewRepository.save(interview);
        interviewRepository.delete(interview);

    }

    @Override
    public Interview updateInterview(Interview interview) {
        InterviewValidator interviewValidator = new InterviewValidator();
        if (interviewValidator.validateInterview(interview)) {
            if (interviewRepository.findById(interview.getInterviewId()).isEmpty())
                throw new InvalidData("Interview Id is not found.");
            else
                return interviewRepository.save(interview);
        } else
            throw new InvalidData(interviewValidator.getErrors());
    }

    @Override
    public Interview addUsersToInterview(int interviewId, int userId) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new InvalidData("Interview Id : " + interviewId + " is not found."));
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidData("User Id : " + userId + " not found."));
        if (interview.getUsers().stream().noneMatch(user1 -> user1.getUserId() == userId)) {
            interview.getUsers().add(user);
            return interviewRepository.save(interview);
        } else
            throw new InvalidData("User Id : " + userId + " is already mapped");
    }

    @Override
    public Interview removeUsersFromInterview(int interviewId, int userId) {
//        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new InvalidData("Interview Id : " + interviewId + " is not found."));
//        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidData("User Id : " + userId + " not found."));
//        if (interview.getUsers().stream().noneMatch(user1 -> user1.getUserId() == userId)) {
//            interview.getUsers().add(user);
//            return interviewRepository.save(interview);
//        } else
//            throw new InvalidData("User Id : " + userId + " is already mapped");
        return null;
    }
}
