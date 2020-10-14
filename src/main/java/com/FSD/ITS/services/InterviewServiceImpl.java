package com.FSD.ITS.services;

import com.FSD.ITS.daos.InterviewRepository;
import com.FSD.ITS.daos.UserRepository;
import com.FSD.ITS.entities.Interview;
import com.FSD.ITS.entities.User;
import com.FSD.ITS.exceptions.InvalidData;
import com.FSD.ITS.validator.InterviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    UserRepository userRepository;

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
    public List<Interview> findAllById(List<Integer> interviewIds) {
        return interviewRepository.findAllById(interviewIds);
    }

    @Override
    @Transactional
    public Interview addInterview(Interview interview) {
        InterviewValidator interviewValidator = new InterviewValidator();
        if (interviewValidator.validateInterview(interview)) {
            if (null == interview.getUsers()) {
                if (interviewRepository.findById(interview.getInterviewId()).isEmpty())
                    interviewRepository.save(interview);
                else
                    throw new InvalidData("Interview Id : " + interview.getInterviewId() + " is already present.");
            } else
                throw new InvalidData("Can't assign users when creating new interview.");
        } else
            throw new InvalidData(interviewValidator.getErrors());
        return interview;
    }

    @Override
    public void deleteInterview(int interviewId) {
        interviewRepository.findById(interviewId).orElseThrow(() -> new InvalidData("Interview Id : " + interviewId + " is not found."));
        interviewRepository.deleteById(interviewId);

    }

    @Override
    public Interview updateInterview(Interview updatedInterview) {
        InterviewValidator interviewValidator = new InterviewValidator();
        if (interviewValidator.validateInterview(updatedInterview)) {
            Interview curInterview = interviewRepository.findById(updatedInterview.getInterviewId()).orElseThrow(() -> new InvalidData("Interview Id : " + updatedInterview.getInterviewId() + " is not found."));
            curInterview.copyInterview(updatedInterview);
            if (null != updatedInterview.getUsers()) {
                List<Integer> userIds = updatedInterview.getUsers().stream().map(User::getUserId).collect(Collectors.toList());
                List<User> users = userRepository.findAllById(userIds);
                curInterview.addUsers(users);
            }
            interviewRepository.save(curInterview);

        } else
            throw new InvalidData(interviewValidator.getErrors());

        return updatedInterview;
    }

    @Override
    public Interview addUsersToInterview(int interviewId, int userId) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new InvalidData("Interview Id : " + interviewId + " is not found."));
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidData("User Id : " + userId + " is not found."));
        if (interview.getUsers().stream().noneMatch(user1 -> user1.getUserId() == userId)) {
            interview.getUsers().add(user);
            interviewRepository.save(interview);
        }
        return interview;
    }

    @Override
    public Interview removeUsersFromInterview(int interviewId, int userId) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new InvalidData("Interview Id is not found."));
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidData("User Id : " + userId + " is not found."));
        interview.getUsers().remove(user);
        return interviewRepository.save(interview);
    }

    @Override
    public Interview updateInterviewStatus(int interviewId, String status) {
        Interview interview = interviewRepository.findById(interviewId).orElseThrow(() -> new InvalidData("Interview Id is not found."));
        interview.setStatus(status);
        return interviewRepository.save(interview);
    }
}
