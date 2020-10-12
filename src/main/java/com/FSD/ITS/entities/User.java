package com.FSD.ITS.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "jsonId")
public class User {
    @Id
    @NotNull(message = "User id is required.")
    private int userId;

    @Column
//    @NotNull(message = "FName is required")
//    @NotBlank(message = "FName can't be blank")
//    @Size(min = 5,max = 30,message = "FName length should be between 5 to 30 Chars")
    private String fname;

    @Column
//    @NotNull(message = "LName is required")
//    @NotBlank(message = "LName can't be blank")
//    @Size(min = 5,max = 30,message = "LName length should be between 5 to 30 Chars")
    private String lName;

    @Column
//    @NotNull(message = "email is required")
//    @NotBlank(message = "email can't be blank")
    private String email;

    @Column
//    @NotNull(message = "mobile is required")
//    @NotBlank(message = "mobile can't be blank")
//    @Size(min = 10,max = 10,message = "mobile length should be between 10 Chars")
//    @Digits(integer = 10, fraction = 0, message = "Digits Error")
    private String mobile;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<InterviewUser> interviewsUser;

    public Set<InterviewUser> getInterviewsUser() {
        return interviewsUser;
    }

    public void setInterviewsUser(Set<InterviewUser> interviewsUser) {
        this.interviewsUser = interviewsUser;
    }

    public User(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User(int userId, @NotNull(message = "FName is required") @NotBlank(message = "FName can't be blank") @Size(min = 5, max = 30, message = "FName length should be between 5 to 30 Chars") String fname, @NotNull(message = "LName is required") @NotBlank(message = "LName can't be blank") @Size(min = 5, max = 30, message = "LName length should be between 5 to 30 Chars") String lName, @NotNull(message = "email is required") @NotBlank(message = "email can't be blank") String email, @NotNull(message = "mobile is required") @NotBlank(message = "mobile can't be blank") @Size(min = 10, max = 10, message = "mobile length should be between 10 Chars") String mobile) {
        this.userId = userId;
        this.fname = fname;
        this.lName = lName;
        this.email = email;
        this.mobile = mobile;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fname='" + fname + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
