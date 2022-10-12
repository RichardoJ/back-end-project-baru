package com.example.backendprojectbaru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.backendprojectbaru.model.Modules;

public interface ModulesRepository extends JpaRepository<Modules, Integer>{
    
    @Query(value = "Select * From modules m WHERE m.course_module_id = :course_id", nativeQuery = true)
    List<Modules> findModulesByCourseId(@Param("course_id") Integer course_id);
}
