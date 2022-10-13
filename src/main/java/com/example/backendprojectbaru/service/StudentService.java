package com.example.backendprojectbaru.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.backendprojectbaru.model.Student;
import com.example.backendprojectbaru.repository.StudentRepository;

@Service
@Transactional
public class StudentService {
    private final StudentRepository studentrepo;

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
        Optional<Student> tmp = studentrepo.findById(id);
        if(tmp.isPresent()){
            return tmp.get();
        }else{
            return null;
        }
    }

    // public Optional<Student> getStudent(Integer id){
    //     Optional<Student> tmp = studentrepo.findById(id);
    //     return tmp;
    // }

    public void deleteStudent(Integer id){
        studentrepo.deleteById(id);
    }
}
