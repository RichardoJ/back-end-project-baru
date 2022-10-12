package com.example.backendprojectbaru.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backendprojectbaru.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer>{
    
}
