package com.example.backendprojectbaru.Controller;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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

import com.example.backendprojectbaru.model.Student;
import com.example.backendprojectbaru.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    private StudentService student_service;

    public StudentController(StudentService studentService){
        this.student_service = studentService;
    }

    @GetMapping("")
    public List<Student> all() {
        return student_service.listAllStudent();
    }

    @PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Student> add(@RequestBody Student user) {
        Student persistedStudent = student_service.saveStudent(user);
        return ResponseEntity.created(URI.create(String.format("/student/%s", persistedStudent.getStudent_name())))
                .body(persistedStudent);
    }

    @GetMapping("/{id}")
    EntityModel<Student> one(@PathVariable Integer id) {
        Student student = student_service.getStudent(id);
        return EntityModel.of(student, linkTo(methodOn(StudentController.class).one(id)).withSelfRel(), linkTo(methodOn(StudentController.class).all()).withRel("student")); 
    }

    

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Integer id) {
       student_service.deleteStudent(id);
    }
}
