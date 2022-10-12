package com.example.backendprojectbaru.model;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Integer id) {
        super("Could not find Student ID : " + id);
      }
}
