package com.FSD.ITS.daos;

import com.FSD.ITS.entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Integer> {
    List<Interview> findByInterviewName(String interviewName);
    List<Interview> findAllByInterviewer(String interviewer);
}
