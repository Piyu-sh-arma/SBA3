package com.FSD.ITS.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "interview_user")
public class InterviewUser {
    @EmbeddedId
    InterviewUserKey id;

    @ManyToOne
    @MapsId("interviewId")
    @JoinColumn(name = "interview_id")
    Interview interview;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    public InterviewUser(InterviewUserKey id, Interview interview, User user) {
        this.id = id;
        this.interview = interview;
        this.user = user;
    }

    public InterviewUser() {
    }

    public InterviewUserKey getId() {
        return id;
    }

    public void setId(InterviewUserKey id) {
        this.id = id;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
