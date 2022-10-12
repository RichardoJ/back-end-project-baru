package com.example.backendprojectbaru.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendprojectbaru.model.Course;
import com.example.backendprojectbaru.model.Enrollment;
import com.example.backendprojectbaru.model.StudentNotFoundException;
import com.example.backendprojectbaru.repository.EnrollmentRepository;

@Service
@Transactional
public class EnrollmentService {
    @Autowired
    EnrollmentRepository enrollmentrepo;
    
    public List<Enrollment> listAllEnrollment(){
        return enrollmentrepo.findAll();
    }

    public Enrollment saveEnrollment(Enrollment user){
        return enrollmentrepo.save(user);
    }

    public Enrollment getEnrollment(Integer id) {
        return enrollmentrepo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<Course> getEnrollmentByStudentId(Integer id){
        List<Enrollment> enrollment = enrollmentrepo.findByStudentId(id);
        List<Course> student_course = new ArrayList<>();
        for (Enrollment enroll : enrollment){
            student_course.add(enroll.getCourse());
        }
        return student_course;
    }

    public void deleteEnrollment(Integer id){
        enrollmentrepo.deleteById(id);
    }
}
