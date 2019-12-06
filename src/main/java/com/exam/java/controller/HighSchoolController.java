package com.exam.java.controller;

import com.exam.java.exception.ResourceNotFoundException;
import com.exam.java.model.HighSchool;
import com.exam.java.model.Subjects;
import com.exam.java.repository.HighSchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.exam.java.model.Academy.*;

/**
 * Controller class responsible for mapping request related to /highschool endpoint
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class HighSchoolController {


    @Autowired
    private HighSchoolRepository highSchoolRepository;

    @GetMapping("/highschool")
    public List<HighSchool> getAllHighSchools() {
        return highSchoolRepository.findAll();
    }

    @GetMapping("/highschool/avgGradesByAcademy")
    public Map<String, Float> getAvgGradesByAcademy(@RequestParam String academy) throws ResourceNotFoundException {
        final List<Float> avgGradesList;
        final Map<String, Float> grades = new HashMap<>();
        if (!academy.equals(BULGARIAN_ACADEMY) && !academy.equals(RUSSIAN_ACADEMY) &&
                !academy.equals(ENGLISH_ACADEMY)) {
            throw new ResourceNotFoundException("Academy with name : " + academy + " is not presented");
        }
        avgGradesList = highSchoolRepository.findAverageOfAllSubjectsBasedOnAcademy((academy));
        grades.put(Subjects.SCIENCE, avgGradesList.get(0));
        grades.put(Subjects.TECHNOLOGY, avgGradesList.get(1));
        grades.put(Subjects.ENGINEERING, avgGradesList.get(2));
        grades.put(Subjects.MATHS, avgGradesList.get(3));
        return grades;
        //    return "Average grades in " + academy + " for STEM subjects is: " + avgGradesList);
    }

    @GetMapping("/highschool/{id}")
    public ResponseEntity<HighSchool> getHighSchoolById(@PathVariable(value = "id") Long highschoolID)
            throws ResourceNotFoundException {
        HighSchool highSchool = highSchoolRepository.findById(highschoolID)
                .orElseThrow(() -> new ResourceNotFoundException("High school not found for this id :: " + highschoolID));
        return ResponseEntity.ok().body(highSchool);
    }

    @PostMapping("/highschools")
    public ResponseEntity<String> createHighSchoolds(@Valid @RequestBody final List<HighSchool> highSchoolList) {
        highSchoolRepository.saveAll(highSchoolList);
        return new ResponseEntity<>("High school inserted successfully", HttpStatus.OK);
    }


    @PostMapping("/highschool")
    public void createStudent(@Valid @RequestBody final HighSchool highSchool) {
        highSchoolRepository.save(highSchool);
    }

    @PutMapping("/highschool/{id}")
    public ResponseEntity<HighSchool> updateHighSchool(@PathVariable(value = "id") Long highschoolID,
                                                       @Valid @RequestBody HighSchool highSchoolDetails) throws ResourceNotFoundException {
        HighSchool highSchool = highSchoolRepository.findById(highschoolID)
                .orElseThrow(() -> new ResourceNotFoundException("High school not found for this id :: " + highschoolID));

        highSchool.setName(highSchoolDetails.getName());
        highSchool.setAddress(highSchoolDetails.getAddress());
        highSchool.setPhone_number(highSchoolDetails.getPhone_number());

        final HighSchool updatedHighSchool = highSchoolRepository.save(highSchool);
        return ResponseEntity.ok(updatedHighSchool);
    }

    @DeleteMapping("/highschool/{id}")
    public Map<String, Boolean> deleteHighSchool(@PathVariable(value = "id") Long highschoolID)
            throws ResourceNotFoundException {
        HighSchool highSchool = highSchoolRepository.findById(highschoolID)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + highschoolID));

        highSchoolRepository.delete(highSchool);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
