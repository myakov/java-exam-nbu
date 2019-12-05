package com.exam.java.model;


import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {


    private long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private int grade;
    private String school;
    private int science_grades;
    private int technology_grades;
    private int engineering_grades;
    private int maths_grades;

    public Student() {
    }

    public Student(String firstName, String lastName, String emailId, int grade, String school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.grade = grade;
        this.school = school;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Column(name = "grade", nullable = false)
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Column(name = "school", nullable = false)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Column(name = "science_grades", nullable = false)
    public int getScience_grades() {
        return science_grades;
    }

    public void setScience_grades(int science_grades) {
        this.science_grades = science_grades;
    }

    @Column(name = "technology_grades", nullable = false)
    public int getTechnology_grades() {
        return technology_grades;
    }

    public void setTechnology_grades(int technology_grades) {
        this.technology_grades = technology_grades;
    }

    @Column(name = "engineering_grades", nullable = false)
    public int getEngineering_grades() {
        return engineering_grades;
    }

    public void setEngineering_grades(int engineering_grades) {
        this.engineering_grades = engineering_grades;
    }

    @Column(name = "maths_grades", nullable = false)
    public int getMaths_grades() {
        return maths_grades;
    }

    public void setMaths_grades(int maths_grades) {
        this.maths_grades = maths_grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", grade=" + grade +
                ", school='" + school + '\'' +
                ", science_grades=" + science_grades +
                ", technology_grades=" + technology_grades +
                ", engineering_grades=" + engineering_grades +
                ", maths_grades=" + maths_grades +
                '}';
    }
}
