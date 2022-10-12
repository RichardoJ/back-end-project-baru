package com.example.backendprojectbaru.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.backendprojectbaru.model.Student;
import com.example.backendprojectbaru.model.StudentNotFoundException;
import com.example.backendprojectbaru.repository.StudentRepository;

@Service
@Transactional
public class StudentService {
    private StudentRepository studentrepo;

    public StudentService(StudentRepository studentRepository){
        this.studentrepo = studentRepository;
    }

    public List<Student> listAllStudent(){
        return studentrepo.findAll();
    }

    public Student saveStudent(Student user){
        return studentrepo.save(user);
    }

    public Student getStudent(Integer id) {
        return studentrepo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public void deleteStudent(Integer id){
        studentrepo.deleteById(id);
    }
}
