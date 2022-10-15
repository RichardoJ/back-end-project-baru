package com.example.backendprojectbaru.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendprojectbaru.model.Course;
import com.example.backendprojectbaru.model.Modules;
import com.example.backendprojectbaru.model.NotFoundException;
import com.example.backendprojectbaru.repository.CourseRepository;

@Service
@Transactional
public class CourseService {
    private final CourseRepository courserepo;

    public CourseService(CourseRepository courseRepository){
        this.courserepo = courseRepository;
    }

    public List<Course> listAllCourse(){
        return courserepo.findAll();
    }

    public Course saveCourse(Course course_tmp){
        return courserepo.save(course_tmp);
    }

    public Course getCourse(Integer id) {
        Optional<Course> course = courserepo.findById(id);
        if(course.isPresent()){
            return course.get();
        }else{
            return null;
        }
    }

    public List<Modules> getCourseModules(Integer id) {
        Optional<Course> course = courserepo.findById(id);
        if(course.isPresent()){
            Course final_course = course.get();
            return final_course.getModules();
        }else{
            return null;
        }
    }

    public void deleteCourse(Integer id){
        courserepo.deleteById(id);
    }
}
