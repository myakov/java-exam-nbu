package com.exam.java.controller;


import com.exam.java.exception.ResourceNotFoundException;
import com.exam.java.model.Teacher;
import com.exam.java.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") Long teacherID)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherID)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherID));
        return ResponseEntity.ok().body(teacher);
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @PutMapping("/teachers/{id}")
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

        final Teacher updatedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/teachers/{id}")
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
