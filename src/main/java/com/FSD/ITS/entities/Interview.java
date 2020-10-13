package com.FSD.ITS.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "interview")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@jsonid")
public class Interview {
    @Id
    @NotNull
    private int interviewId;

    @NotNull
    @Column(name = "title")
    private String interviewName;

    @NotNull
    @Column(name = "interviewer")
    private String interviewer;

    @NotNull
    @Column(name = "skills")
    private String skills;

    @Column(name = "start_time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;

    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Column(name = "status")
    private String status;

    @NotNull
    @Column(name = "remarks")
    private String remarks;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "interview_user",
            joinColumns = {@JoinColumn(name = "interview_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users;

    public Interview() {
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interview interview = (Interview) o;
        return interviewId == interview.interviewId &&
                Objects.equals(interviewName, interview.interviewName) &&
                Objects.equals(interviewer, interview.interviewer) &&
                Objects.equals(skills, interview.skills) &&
                Objects.equals(time, interview.time) &&
                Objects.equals(date, interview.date) &&
                Objects.equals(status, interview.status) &&
                Objects.equals(remarks, interview.remarks) &&
                Objects.equals(users, interview.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewId, interviewName, interviewer, skills, time, date, status, remarks, users);
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewId=" + interviewId +
                ", interviewName='" + interviewName + '\'' +
                ", interviewer='" + interviewer + '\'' +
                ", skills='" + skills + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                ", users=" + users +
                '}';
    }
}
