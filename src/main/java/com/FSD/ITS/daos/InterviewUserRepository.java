package com.FSD.ITS.daos;

import com.FSD.ITS.entities.Interview;
import com.FSD.ITS.entities.InterviewUser;
import com.FSD.ITS.entities.InterviewUserKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewUserRepository extends JpaRepository<InterviewUser , InterviewUserKey> {
//    List<Interview> findByInterviewName(String interviewName);
//    List<Interview> findAllByInterviewer(String interviewer);
}
