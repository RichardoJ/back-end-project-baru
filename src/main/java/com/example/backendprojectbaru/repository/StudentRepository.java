package com.example.backendprojectbaru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backendprojectbaru.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    
}
