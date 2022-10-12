package com.example.backendprojectbaru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendprojectbaru.model.Assignment;
import com.example.backendprojectbaru.model.Modules;
import com.example.backendprojectbaru.model.StudentNotFoundException;
import com.example.backendprojectbaru.repository.AssignmentRepository;
import com.example.backendprojectbaru.repository.ModulesRepository;

@Service
@Transactional
public class ModuleService {
    @Autowired
    ModulesRepository modulesrepo;

    @Autowired
    AssignmentRepository assignmentrepo;

    public List<Modules> listAllModules(){
        return modulesrepo.findAll();
    }

    public Modules saveModule(Modules module){
        return modulesrepo.save(module);
    }

    public Modules getModule(Integer id) {
        return modulesrepo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public void deleteModule(Integer id){
        modulesrepo.deleteById(id);
    }

    public Assignment saveAssignment(Integer id, Assignment assignment){
        Optional<Modules> save_module = modulesrepo.findById(id);
        if(save_module.isPresent()){
            assignment.setModule(save_module.get());
            return assignmentrepo.save(assignment);
        }
        else{
            Assignment empty = new Assignment();
            return empty;
        }
        
    }

    public List<Assignment> getModuleAssignment(Integer id){
        Optional<Modules> module_tmp = modulesrepo.findById(id);
        if(module_tmp.isPresent()){
            Modules final_module = module_tmp.get();
            return final_module.getAssignments();
        }
        else{
            List<Assignment> empty = new ArrayList();
            return empty;
        }
    }

    public List<Assignment> getAllModuleAssignments(){
        List<Modules> modules = modulesrepo.findAll();
        List<Assignment> allAssignment = new ArrayList<>();
        List<Assignment> assignmentsPerModules;
        for (Modules modules2 : modules) {
            assignmentsPerModules = modules2.getAssignments();
            allAssignment.addAll(assignmentsPerModules);
        }
        return allAssignment;
    }

    public List<Assignment> getAllAssignmentsByCourse(Integer id){
        List<Modules> modules = modulesrepo.findModulesByCourseId(id);
        List<Assignment> allAssignment = new ArrayList<>();
        List<Assignment> assignmentsPerCourse;
        for (Modules modules2 : modules) {
            assignmentsPerCourse = modules2.getAssignments();
            allAssignment.addAll(assignmentsPerCourse);
        }
        return allAssignment;
    }
}
