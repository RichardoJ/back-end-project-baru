package com.example.backendprojectbaru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendprojectbaru.model.Course;
import com.example.backendprojectbaru.model.Enrollment;
import com.example.backendprojectbaru.model.NotFoundException;
import com.example.backendprojectbaru.repository.EnrollmentRepository;

@Service
@Transactional
public class EnrollmentService {
    private final  EnrollmentRepository enrollmentrepo;

    public  EnrollmentService(EnrollmentRepository enrollmentRepository){
        this.enrollmentrepo = enrollmentRepository;
    }
    
    public List<Enrollment> listAllEnrollment(){
        return enrollmentrepo.findAll();
    }

    public Enrollment saveEnrollment(Enrollment user){
        return enrollmentrepo.save(user);
    }

    public Enrollment getEnrollment(Integer id) {
        Optional<Enrollment> tmp = enrollmentrepo.findById(id);
        if(tmp.isEmpty()){
            return null;
        }else{
            return tmp.get();
        }
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
