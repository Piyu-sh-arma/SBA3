package com.FSD.ITS.services;

import com.FSD.ITS.entities.Interview;

import java.util.List;

public interface InterviewService {
    Interview addInterview(Interview interview);
    void deleteInterview(int interviewId);
    Interview updateInterview(Interview interview);
    List<Interview> getInterviews();
    int getInterviewsCount();
    Interview findByInterviewId(int interviewId);
    List<Interview> findAllByInterviewName(String interviewName);
    List<Interview> findAllByInterviewer(String interviewer);
}
