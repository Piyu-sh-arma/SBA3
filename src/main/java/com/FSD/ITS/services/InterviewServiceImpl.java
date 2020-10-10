package com.FSD.ITS.services;

import com.FSD.ITS.daos.InterviewRepository;
import com.FSD.ITS.entities.Interview;
import com.FSD.ITS.exceptions.InvalidData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    InterviewRepository interviewRepository;

    @Override
    public List<Interview> getInterviews() {
        return interviewRepository.findAll();
    }

    @Override
    public Interview addInterview(Interview interview) {
        return interviewRepository.save(interview);
    }

    @Override
    public String deleteInterview(int interviewId) {
        return null;
    }

    @Override
    public Interview updateInterview(Interview interview) {
        return null;
    }

    @Override
    public int getInterviewsCount() {
        return 0;
    }

    @Override
    public Interview findByInterviewId(int interviewId) {
        return interviewRepository.findById(interviewId).orElseThrow(()->new InvalidData("Interview Id"+interviewId+" is missing is db."));
    }

    @Override
    public Interview findByInterviewName(String interviewName) {
        return null;
    }

    @Override
    public List<Interview> findAllByInterviewer(String interviewer) {
        return null;
    }
}
