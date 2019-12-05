package com.exam.java.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.exam.java.exception.ResourceNotFoundException;
import com.exam.java.model.Student;
import com.exam.java.model.Subjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.java.repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> getAllstudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/avgGrades")
    public String getAvgGradeByAcademyandSubjects(@RequestParam String subject, String academy) throws ResourceNotFoundException {

        if (subject.equalsIgnoreCase(Subjects.SCIENCE)) {
            return "Average Science grades in " + academy + " is: " + studentRepository.findAvgScienceGrdesByAcademy(academy);
        } else if (subject.equalsIgnoreCase(Subjects.TECHOLOGY)) {
            return "Average Technology grades in " + academy + " is: " + studentRepository.findAvgTechnologyGrdesByAcademy(academy);
        } else if (subject.equalsIgnoreCase(Subjects.ENGINEERING)) {
            return "Average Engineering grades in " + academy + " is: " + studentRepository.findAvgEngGrdesByAcademy(academy);
        } else if (subject.equalsIgnoreCase(Subjects.MATHS)) {
            return "Average Maths grades in " + academy + " is: " + studentRepository.findAvgMathsGrdesByAcademy(academy);
        } else
            throw new ResourceNotFoundException("Subject:  '" + subject + "' are not found");
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentsById(@PathVariable(value = "id") Long studentID)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentID));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/students")
    public ResponseEntity<String> createStudents(@Valid @RequestBody final List<Student> studentList) {
        studentRepository.saveAll(studentList);
        return new ResponseEntity<>("Students inserted successfully", HttpStatus.OK);
    }


    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody final Student student) {
        studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentID,
                                                 @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentID));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmailId(studentDetails.getEmailId());
        student.setGrade(studentDetails.getGrade());
        student.setSchool(studentDetails.getSchool());
        student.setScience_grades(studentDetails.getScience_grades());
        student.setTechnology_grades(studentDetails.getTechnology_grades());
        student.setEngineering_grades(studentDetails.getEngineering_grades());
        student.setMaths_grades(studentDetails.getMaths_grades());
        final Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/student/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentID)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentID));

        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
