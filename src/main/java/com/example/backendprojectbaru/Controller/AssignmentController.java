package com.example.backendprojectbaru.Controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backendprojectbaru.model.Assignment;
import com.example.backendprojectbaru.model.NotFoundException;
import com.example.backendprojectbaru.service.AssignmentService;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    private final AssignmentService assignment_service;

    public AssignmentController(AssignmentService assignmentService){
        this.assignment_service = assignmentService;
    }

    @GetMapping("")
    public List<Assignment> all(){
        return assignment_service.listAllAssignment();
    }

    @GetMapping("/{id}")
    EntityModel<Assignment> one(@PathVariable Integer id) {
        Assignment assignment = assignment_service.getAssignment(id);
        if(assignment == null){
            throw new NotFoundException(id);
        }else{
            return EntityModel.of(assignment, linkTo(methodOn(AssignmentController.class).one(id)).withSelfRel(), linkTo(methodOn(AssignmentController.class).all()).withRel("assignment")); 
        }
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        assignment_service.deleteAssignment(id);
    }
}
