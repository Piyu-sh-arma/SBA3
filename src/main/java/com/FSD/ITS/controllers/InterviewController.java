package com.FSD.ITS.controllers;

import com.FSD.ITS.entities.Interview;
import com.FSD.ITS.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @GetMapping("/interviews")
    public List<Interview> getInterviews(){
        return interviewService.getInterviews();
    }

    @PostMapping("/interviews")
    public Interview addInterview(Interview interview){
        return interviewService.addInterview(interview);
    }

    @GetMapping("/interviews/{id}")
    public Interview getInterviewById(@PathVariable(value = "id") int id){
        return interviewService.findByInterviewId(id);
    }

}
