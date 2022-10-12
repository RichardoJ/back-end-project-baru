package com.example.backendprojectbaru.Controller;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendprojectbaru.model.Course;
import com.example.backendprojectbaru.model.Modules;
import com.example.backendprojectbaru.service.CourseService;


@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService course_service;

    @GetMapping("")
    public List<Course> all() {
        return course_service.listAllCourse();
    }

    @GetMapping("/{id}")
    EntityModel<Course> one(@PathVariable Integer id) {
        Course course = course_service.getCourse(id);

        return EntityModel.of(course, linkTo(methodOn(CourseController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CourseController.class).all()).withRel("course"));
    }

    @PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Course> add(@RequestBody Course course_tmp) {
        Course persistedCourse = course_service.saveCourse(course_tmp);
        return ResponseEntity.created(URI.create(String.format("/course/%s", persistedCourse.getCourse_name())))
                .body(persistedCourse);
    }

    @GetMapping("/{id}/modules")
    public List<Modules> all_modules(@PathVariable Integer id) {
        return course_service.getCourseModules(id);
    }

    @DeleteMapping("/{id}")
    void deleteCourse(@PathVariable Integer id) {
        course_service.deleteCourse(id);
    }
}
