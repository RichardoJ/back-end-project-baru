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

import com.example.backendprojectbaru.model.Assignment;
import com.example.backendprojectbaru.model.Modules;
import com.example.backendprojectbaru.model.StudentNotFoundException;
import com.example.backendprojectbaru.repository.AssignmentRepository;
import com.example.backendprojectbaru.repository.ModulesRepository;
import com.example.backendprojectbaru.service.ModuleService;

@RestController
@RequestMapping("/modules")
public class ModulesController {
    @Autowired
    ModuleService module_service;

    @GetMapping("")
    public List<Modules> all() {
        return module_service.listAllModules();
    }

    @PostMapping(value = "/{id}/assignment", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Assignment> add(@PathVariable Integer id, @RequestBody Assignment user) {
        Assignment persistedAssignment = module_service.saveAssignment(id, user);
        
        return ResponseEntity.created(URI.create(String.format("/assignment/%s", persistedAssignment.getAssignment_name())))
                .body(persistedAssignment);
    }

    @GetMapping("/{id}")
    EntityModel<Modules> one(@PathVariable Integer id) {
        Modules module = module_service.getModule(id);

        return EntityModel.of(module, linkTo(methodOn(ModulesController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ModulesController.class).all()).withRel("modules"));
    }

    //Cari semua assignment sesuai module
    @GetMapping("/{id}/assignment")
    public List<Assignment> findAssignment(@PathVariable Integer id){
         return module_service.getModuleAssignment(id);
    }

    //Untuk cari semua assignment terserah mau module berapa
    @GetMapping("/assignment")
    public List<Assignment> allAssignmentPerModules() {
        return module_service.getAllModuleAssignments();
    }

    //cari semua assignment tergantung course
    @GetMapping("/{id}/assignmentByCourse")
    public List<Assignment> allAssignmentPerCourse(@PathVariable Integer id) {
        return module_service.getAllAssignmentsByCourse(id);
    }

    @DeleteMapping("/{id}")
    void deleteModules(@PathVariable Integer id) {
        module_service.deleteModule(id);
    }
}
