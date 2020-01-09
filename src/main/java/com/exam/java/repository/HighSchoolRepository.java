package com.exam.java.repository;

import com.exam.java.model.HighSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HighSchoolRepository extends JpaRepository<HighSchool, Long> {

    @Query(value = "SELECT CAST(AVG(science_grades) AS Float(5)) from public.students where school = ?1 union all " +
            "SELECT CAST(AVG(technology_grades) AS DECIMAL(10,2)) from public.students where school = ?1 union all " +
            "SELECT CAST(AVG(engineering_grades) AS DECIMAL(10,2)) from public.students where school = ?1 union all " +
            "SELECT CAST(AVG(maths_grades) AS DECIMAL(10,2)) from public.students where school = ?1", nativeQuery = true)
    List<Float> findAverageOfAllSubjectsBasedOnAcademy(String academy);
}
