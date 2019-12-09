package com.exam.java.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.exam.java.exception.ResourceNotFoundException;
import com.exam.java.model.Academy;
import com.exam.java.model.Student;
import com.exam.java.model.Subjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.exam.java.repository.StudentRepository;

/**
 * Controller class responsible for mapping request related to /students endpoint
 * and filtering requests based on authorization rules declared in @PreAuthorize clause
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('STANDARD_USER')")
    public List<Student> getAllstudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/avgStudendGrades")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('STANDARD_USER')")
    public Float getAvgGradesByStudent(@RequestParam String first_name, String last_name) throws ResourceNotFoundException {
        float avgGrade = studentRepository.findAvgGradesByStudent(first_name, last_name);
        return avgGrade / 4;
    }

    @GetMapping("/students/avgGrades")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('STANDARD_USER')")
    public String getAvgGradeByAcademyandSubjects(@RequestParam String subject, String academy) throws ResourceNotFoundException {

        if (subject.equalsIgnoreCase(Subjects.SCIENCE)) {
            return "Average Science grades in " + academy + " is: " + studentRepository.findAvgScienceGrdesByAcademy(academy);
        } else if (subject.equalsIgnoreCase(Subjects.TECHNOLOGY)) {
            return "Average Technology grades in " + academy + " is: " + studentRepository.findAvgTechnologyGrdesByAcademy(academy);
        } else if (subject.equalsIgnoreCase(Subjects.ENGINEERING)) {
            return "Average Engineering grades in " + academy + " is: " + studentRepository.findAvgEngGrdesByAcademy(academy);
        } else if (subject.equalsIgnoreCase(Subjects.MATHS)) {
            return "Average Maths grades in " + academy + " is: " + studentRepository.findAvgMathsGrdesByAcademy(academy);
        } else
            throw new ResourceNotFoundException("Subject:  '" + subject + "' are not found");
    }

    @GetMapping("/students/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Student> getStudentsById(@PathVariable(value = "id") Long studentID)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentID));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/students")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> createStudents(@Valid @RequestBody final List<Student> studentList) {
        studentRepository.saveAll(studentList);
        return new ResponseEntity<>("Students inserted successfully", HttpStatus.OK);
    }

    @PostMapping("/student")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> createStudent(@Valid @RequestBody final Student student) {
        studentRepository.save(student);
        return new ResponseEntity<>("Students inserted successfully", HttpStatus.OK);

    }

    @PutMapping("/students/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
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

    @PutMapping("/students/uk/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('ENGLISH_TEACHER')")
    public ResponseEntity<Student> updateEnglishStudentInfo(@PathVariable(value = "id") Long studentID,
                                                            @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentID));

        if (!student.getSchool().equalsIgnoreCase(Academy.ENGLISH_ACADEMY)) {
            throw new ResourceNotFoundException("Student " + studentID + " is not in English academy");
        }
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

    @PutMapping("/students/bg/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('BULGARIAN_TEACHER')")
    public ResponseEntity<Student> updateBulgarianStudentInfo(@PathVariable(value = "id") Long studentID,
                                                              @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentID));

        if (!student.getSchool().equalsIgnoreCase(Academy.BULGARIAN_ACADEMY)) {
            throw new ResourceNotFoundException("Student " + studentID + " is not in Bulgarian academy");
        }
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

    @PutMapping("/students/rus/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('RUSSIAN_TEACHER')")
    public ResponseEntity<Student> updateRussianStudentInfo(@PathVariable(value = "id") Long studentID,
                                                            @Valid @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentID));

        if (!student.getSchool().equalsIgnoreCase(Academy.RUSSIAN_ACADEMY)) {
            throw new ResourceNotFoundException("Student " + studentID + " is not in Russian academy");
        }
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
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
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
