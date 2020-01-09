package com.exam.java.controller;


import com.exam.java.exception.ResourceNotFoundException;
import com.exam.java.model.Teacher;
import com.exam.java.repository.TeacherRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class responsible for mapping request related to /teachers endpoint
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
@Api(value="Teacher Controller")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teachers")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('STANDARD_USER')")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") Long teacherID)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherID)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherID));
        return ResponseEntity.ok().body(teacher);
    }

    @PostMapping("/teachers")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<String> createTeachers(@Valid @RequestBody List<Teacher> teacherList) {
        teacherRepository.saveAll(teacherList);
        return new ResponseEntity<>("Teachers inserted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @PostMapping("/teacher")
    public ResponseEntity<String> createTeacher(@Valid @RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return new ResponseEntity<>("Teacher inserted successfully", HttpStatus.OK);
    }

    @PutMapping("/teachers/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") Long teacherID,
                                                 @Valid @RequestBody Teacher teacherDetails) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherID)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherID));

        teacher.setFirstName(teacherDetails.getFirstName());
        teacher.setLastName(teacherDetails.getLastName());
        teacher.setEmailId(teacherDetails.getEmailId());
        teacher.setFirst_subject(teacherDetails.getFirst_subject());
        teacher.setSecond_subject(teacherDetails.getSecond_subject());
        teacher.setThird_subject(teacherDetails.getThird_subject());
        teacher.setFourth_subject(teacherDetails.getFourth_subject());

        final Teacher updatedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/teachers/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") Long teacherID)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherID)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherID));

        teacherRepository.delete(teacher);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
