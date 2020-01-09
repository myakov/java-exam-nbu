package com.exam.java.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

/**
 * Model class representing Teacher entity
 */

@Entity
@Table(name = "teacher")
@ApiModel(description = "All details about the Teachers. ")
public class Teacher {

    private String firstName;
    private String lastName;
    private String emailId;
    private String first_subject;
    private String second_subject;
    private String third_subject;
    private String fourth_subject;
    private long teacherID;

    public Teacher() {
    }

    @Id
    @GeneratedValue
    public long getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(long teacherID) {
        this.teacherID = teacherID;
    }

    @Column(name = "first_subject", nullable = false)
    public String getFirst_subject() {
        return first_subject;
    }

    public void setFirst_subject(String first_subject) {
        this.first_subject = first_subject;
    }

    @Column(name = "second_subject")
    public String getSecond_subject() {
        return second_subject;
    }

    public void setSecond_subject(String second_subject) {
        this.second_subject = second_subject;
    }

    @Column(name = "third_subject")
    public String getThird_subject() {
        return third_subject;
    }

    public void setThird_subject(String third_subject) {
        this.third_subject = third_subject;
    }

    @Column(name = "fourth_subject")
    public String getFourth_subject() {
        return fourth_subject;
    }

    public void setFourth_subject(String fourth_subject) {
        this.fourth_subject = fourth_subject;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = false, unique = true)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", first_subject='" + first_subject + '\'' +
                ", second_subject='" + second_subject + '\'' +
                ", third_subject='" + third_subject + '\'' +
                ", fourth_subject='" + fourth_subject + '\'' +
                ", teacherID=" + teacherID +
                '}';
    }
}