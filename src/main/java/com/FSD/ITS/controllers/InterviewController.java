package com.FSD.ITS.controllers;

import com.FSD.ITS.entities.Interview;
import com.FSD.ITS.entities.User;
import com.FSD.ITS.services.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {
    @Autowired
    InterviewService interviewService;

    @GetMapping({"", "/"})
    public List<Interview> getInterviews() {
        return interviewService.getInterviews();
    }

    @GetMapping("/{id}")
    public Interview getInterviewById(@PathVariable(value = "id") int id) {
        return interviewService.findByInterviewId(id);
    }

    @GetMapping("/interview/{name}")
    public List<Interview> getInterviewByName(@PathVariable(value = "name") String name) {
        return interviewService.findAllByInterviewName(name);
    }

    @GetMapping("/interviewer/{name}")
    public List<Interview> getInterviewBy(@PathVariable(value = "name") String interviewer) {
        return interviewService.findAllByInterviewer(interviewer);
    }

    @GetMapping("/attendees/{interviewId}")
    public List<User> getAttendees(@PathVariable(value = "interviewId") int interviewId) {
        return interviewService.findByInterviewId(interviewId).getUsers();
    }

    @GetMapping("/count")
    public int getInterviewCount() {

        return interviewService.getInterviewsCount();
    }

    @PostMapping({"", "/"})
    public Interview addInterview(@RequestBody Interview interview) {

        return interviewService.addInterview(interview);
    }

    @PutMapping({"", "/"})
    public Interview updateInterview(@RequestBody Interview interview) {
        return interviewService.addInterview(interview);
    }

    @DeleteMapping({"/{id}"})
    public void deleteInterview(@PathVariable(value = "id") int id) {
        interviewService.deleteInterview(id);
    }

}
