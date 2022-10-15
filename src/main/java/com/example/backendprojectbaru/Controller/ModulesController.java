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

import com.example.backendprojectbaru.model.Assignment;
import com.example.backendprojectbaru.model.Modules;
import com.example.backendprojectbaru.model.NotFoundException;
import com.example.backendprojectbaru.service.ModuleService;

@RestController
@RequestMapping("/modules")
public class ModulesController {

    private final ModuleService module_service;

    public ModulesController(ModuleService moduleService){
        this.module_service = moduleService;
    }

    @GetMapping("")
    public List<Modules> all() {
        return module_service.listAllModules();
    }

    @GetMapping("/{id}")
    EntityModel<Modules> one(@PathVariable Integer id) {
        Modules module = module_service.getModule(id);
        if(module == null){
            throw new NotFoundException(id);
        }

        return EntityModel.of(module, linkTo(methodOn(ModulesController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ModulesController.class).all()).withRel("modules"));
    }

    @PostMapping(value = "/", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Modules> add(@RequestBody Modules module) {
        Modules persistedModule = module_service.saveModule(module);

        return ResponseEntity.created(URI.create(String.format("/modules/%s", persistedModule.getModules_name())))
                .body(persistedModule);
    }

    @PostMapping(value = "/{id}/assignment", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Assignment> add(@PathVariable Integer id, @RequestBody Assignment module) {
        Assignment persistedAssignment = module_service.saveAssignment(id, module);

        return ResponseEntity.created(URI.create(String.format("/assignment/%s", persistedAssignment.getAssignment_name())))
                .body(persistedAssignment);
    }

    //Cari semua assignment sesuai module
    @GetMapping("/{id}/assignment")
    public List<Assignment> findAssignment(@PathVariable Integer id){
        List<Assignment> assignments = module_service.getModuleAssignment(id);
        if(assignments == null){
            throw new NotFoundException(id);
        }
        return assignments;
    }

    //Untuk cari semua assignment terserah mau module berapa
    @GetMapping("/assignment")
    public List<Assignment> allAssignmentPerModules() {
        return module_service.getAllModuleAssignments();
    }

    //cari semua assignment tergantung course
    @GetMapping("/{id}/assignmentByCourse")
    public List<Assignment> allAssignmentPerCourse(@PathVariable Integer id) {
        List<Assignment> assignments = module_service.getAllAssignmentsByCourse(id);
        if(assignments == null){
            throw new NotFoundException(id);
        }
        return assignments;
    }

    @DeleteMapping("/{id}")
    void deleteModules(@PathVariable Integer id) {
        module_service.deleteModule(id);
    }
}
