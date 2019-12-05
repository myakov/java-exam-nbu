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


}
