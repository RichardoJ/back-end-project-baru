package com.example.backendprojectbaru.Controller;

import java.net.URI;
import java.util.ArrayList;
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
import com.example.backendprojectbaru.model.Enrollment;
import com.example.backendprojectbaru.model.StudentNotFoundException;
import com.example.backendprojectbaru.repository.EnrollmentRepository;
import com.example.backendprojectbaru.service.EnrollmentService;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentService enrollment_service;

    @GetMapping("")
    public List<Enrollment> all() {
        return enrollment_service.listAllEnrollment();
    }

    @PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Enrollment> add(@RequestBody Enrollment user) {
        Enrollment persistedEnrollment = enrollment_service.saveEnrollment(user);
        return ResponseEntity.created(URI.create(String.format("/enrollment/%s", persistedEnrollment.getId())))
                .body(persistedEnrollment);
    }

    @GetMapping("/{id}")
    EntityModel<Enrollment> one(@PathVariable Integer id) {
        Enrollment enrollment = enrollment_service.getEnrollment(id);
        
        return EntityModel.of(enrollment, linkTo(methodOn(EnrollmentController.class).one(id)).withSelfRel(), linkTo(methodOn(EnrollmentController.class).all()).withRel("enrollment")); 
    }

    @GetMapping("/student_id/{id}/course")
    public List<Course> findbystudent(@PathVariable Integer id) {
        return enrollment_service.getEnrollmentByStudentId(id);
    }

    @DeleteMapping("/{id}")
    void deleteEnrollment(@PathVariable Integer id) {
        enrollment_service.deleteEnrollment(id);
    }
}
