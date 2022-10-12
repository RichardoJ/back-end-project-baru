package com.example.backendprojectbaru.Controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendprojectbaru.model.Assignment;
import com.example.backendprojectbaru.model.StudentNotFoundException;
import com.example.backendprojectbaru.repository.AssignmentRepository;
import com.example.backendprojectbaru.service.AssignmentService;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    @Autowired
    AssignmentService assignment_service;

    @GetMapping("")
    public List<Assignment> all(){
        return assignment_service.listAllAssignment();
    }

    @GetMapping("/{id}")
    EntityModel<Assignment> one(@PathVariable Integer id) {
        Assignment assignment = assignment_service.getAssignment(id);
        
        return EntityModel.of(assignment, linkTo(methodOn(AssignmentController.class).one(id)).withSelfRel(), linkTo(methodOn(AssignmentController.class).all()).withRel("assignment")); 
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        assignment_service.deleteAssignment(id);
    }
}
