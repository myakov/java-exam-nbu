package com.exam.java.controller;

import com.exam.java.exception.ResourceNotFoundException;
import com.exam.java.model.Student;
import com.exam.java.repository.StudentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    StudentRepository studentRepository;


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllStudents() {

        //given
        Student s = new Student();
        s.setId(1);
        s.setFirstName("test");
        s.setLastName("test");
        s.setEmailId("test");
        s.setGrade(5);
        s.setScience_grades(4);
        s.setTechnology_grades(4);
        s.setEngineering_grades(5);
        s.setMaths_grades(3);

        Student s2 = new Student();
        s2.setId(2);
        s2.setFirstName("test2");
        s2.setLastName("test2");
        s2.setEmailId("test2");
        s2.setGrade(6);
        s2.setScience_grades(4);
        s2.setTechnology_grades(4);
        s2.setEngineering_grades(5);
        s2.setMaths_grades(3);

        List<Student> studentList = new ArrayList<>();
        studentList.add(s);
        studentList.add(s2);

        //when
        when(studentController.getAllstudents()).thenReturn(studentList);

        List<Student> studentList2 = studentController.getAllstudents();

        //then
        verify(studentRepository).findAll();

        assertEquals(studentList, studentList2);
    }

//    @Test
//    public void getAvgGradesByStudent() throws ResourceNotFoundException {
//        //given
//        Student s = new Student();
//        s.setId(1);
//        s.setFirstName("test");
//        s.setLastName("test");
//        s.setEmailId("test");
//        s.setGrade(5);
//        s.setScience_grades(4);
//        s.setTechnology_grades(4);
//        s.setEngineering_grades(5);
//        s.setMaths_grades(3);
//
//        float avg = (s.getScience_grades() + s.getTechnology_grades() + s.getEngineering_grades() + s.getMaths_grades()) / 4;
//
//        //when
//        when(studentController.getAvgGradesByStudent(s.getFirstName(), s.getFirstName())).thenReturn(avg);
//        float avgGrade = studentController.getAvgGradesByStudent(s.getFirstName(), s.getLastName());
//
//        //then
//        assertEquals(String.valueOf(avg), String.valueOf(avgGrade));
//
//    }

    @Test
    public void getAvgGradeByAcademyandEngSubjects() throws ResourceNotFoundException {
        //given
        Student s = new Student();
        s.setId(1);
        s.setFirstName("test");
        s.setLastName("test");
        s.setEmailId("test");
        s.setGrade(5);
        s.setScience_grades(4);
        s.setTechnology_grades(4);
        s.setEngineering_grades(5);
        s.setMaths_grades(3);
        s.setSchool("English academy");

        when(studentController.getAvgGradeByAcademyandSubjects("Engineering", s.getSchool())).thenReturn("4");

        String avgGrade = studentController.getAvgGradeByAcademyandSubjects("Engineering", s.getSchool());
        assertEquals("Average Engineering grades in English academy is: 4", avgGrade);
    }

    @Test
    public void getAvgGradeByBgAcademyandMathsSubjects() throws ResourceNotFoundException {
        //given
        Student s = new Student();
        s.setId(1);
        s.setFirstName("test");
        s.setLastName("test");
        s.setEmailId("test");
        s.setGrade(5);
        s.setScience_grades(4);
        s.setTechnology_grades(4);
        s.setEngineering_grades(5);
        s.setMaths_grades(3);
        s.setSchool("English academy");

        when(studentController.getAvgGradeByAcademyandSubjects("Maths", s.getSchool())).thenReturn("4");

        String avgGrade = studentController.getAvgGradeByAcademyandSubjects("Maths", s.getSchool());
        assertEquals("Average Maths grades in English academy is: 4", avgGrade);
    }


    @Test
    public void getAvgGradeByBgAcademyandTechnologySubjects() throws ResourceNotFoundException {
        //given
        Student s = new Student();
        s.setId(1);
        s.setFirstName("test");
        s.setLastName("test");
        s.setEmailId("test");
        s.setGrade(5);
        s.setScience_grades(4);
        s.setTechnology_grades(4);
        s.setEngineering_grades(5);
        s.setMaths_grades(3);
        s.setSchool("English academy");

        when(studentController.getAvgGradeByAcademyandSubjects("Technology", s.getSchool())).thenReturn("4");

        String avgGrade = studentController.getAvgGradeByAcademyandSubjects("Technology", s.getSchool());
        assertEquals("Average Technology grades in English academy is: 4", avgGrade);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getAvgGradeByAcademyandSubjects_ThrowException_InvalidSubject() throws ResourceNotFoundException {
        //given
        Student s = new Student();

        s.setSchool("English academy");

        when(studentController.getAvgGradeByAcademyandSubjects("art", s.getSchool())).thenThrow(ResourceNotFoundException.class);
    }

    @Test
    public void getStudentsById() {
    }

    @Test
    public void createStudents() {
    }

    @Test
    public void createStudent() {
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void updateEnglishStudentInfo() {
    }

    @Test
    public void updateBulgarianStudentInfo() {
    }

    @Test
    public void updateRussianStudentInfo() {
    }

    @Test
    public void deleteStudent() {
    }
}