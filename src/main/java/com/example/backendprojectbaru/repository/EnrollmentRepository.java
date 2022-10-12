package com.example.backendprojectbaru.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backendprojectbaru.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
    
    @Query(value = "SELECT * FROM enrollment WHERE student_id = :student_id", nativeQuery = true)
    List<Enrollment> findByStudentId(@Param("student_id") Integer student_id);
}
