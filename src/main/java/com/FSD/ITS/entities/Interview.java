package com.FSD.ITS.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewId;

    @Column(name = "title")
    private String interviewName;

    @Column(name="interviewer")
    private String interviewerName;

    @Column(name="interviewee_id")
    private int intervieweeId;

    @Transient
    private String intervieweeName;

    @Column(name="skills")
    private String usersSkills;

    @Column(name = "start_time")
    private LocalTime time;

    @Column(name="start_date")
    private LocalDate date;

    @Column(name="status")
    private String interviewStatus;

    @Column(name = "remarks")
    private String remarks;
}
