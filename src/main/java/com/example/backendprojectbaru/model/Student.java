package com.example.backendprojectbaru.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Enrollment> enrollments;
    
    private String student_name;
    private String student_email;
    private String student_password;
    private String student_address;
    private int student_semester;
}
