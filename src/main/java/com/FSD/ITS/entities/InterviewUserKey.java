package com.FSD.ITS.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InterviewUserKey implements Serializable {
    @Column(name = "interview_id")
    int interviewId;
    @Column(name = "user_id")
    int userId;

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterviewUserKey that = (InterviewUserKey) o;
        return interviewId == that.interviewId &&
                userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewId, userId);
    }

    public InterviewUserKey(int interviewId, int userId) {
        this.interviewId = interviewId;
        this.userId = userId;
    }

    public InterviewUserKey() {
    }
}