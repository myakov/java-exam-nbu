package com.exam.java.repository;

import com.exam.java.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.text.DecimalFormat;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query(value = "SELECT CAST(AVG(science_grades) AS DECIMAL(10,2)) from public.students where school = ?1", nativeQuery = true)
    String findAvgScienceGrdesByAcademy(String academyName);

    @Query(value = "SELECT CAST(AVG(technology_grades) AS DECIMAL(10,2)) from public.students where school = ?1", nativeQuery = true)
    String findAvgTechnologyGrdesByAcademy(String academyName);

    @Query(value = "SELECT CAST(AVG(engineering_grades) AS DECIMAL(10,2)) from public.students where school = ?1", nativeQuery = true)
    String findAvgEngGrdesByAcademy(String academyName);

    @Query(value = "SELECT CAST(AVG(maths_grades) AS DECIMAL(10,2)) from public.students where school = ?1", nativeQuery = true)
    String findAvgMathsGrdesByAcademy(String academyName);

    @Query(value = "SELECT (science_grades + technology_grades + engineering_grades + maths_grades) FROM students where first_name = ?1 and last_name = ?2", nativeQuery = true)
    float findAvgGradesByStudent(String firstName, String lastName);
}
