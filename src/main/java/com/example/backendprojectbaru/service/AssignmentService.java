package com.example.backendprojectbaru.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendprojectbaru.model.Assignment;
import com.example.backendprojectbaru.model.StudentNotFoundException;
import com.example.backendprojectbaru.repository.AssignmentRepository;

@Service
@Transactional
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentrepo;


    public List<Assignment> listAllAssignment(){
        return assignmentrepo.findAll();
    }

    public Assignment saveAssignment(Assignment assignment_tmp){
        return assignmentrepo.save(assignment_tmp);
    }

    public Assignment getAssignment(Integer id) {
        // Optional<Assignment> assignment = assignmentrepo.findById(id);
        // if(assignment.isPresent()){
        //     Assignment final_assignment = assignment.get();
        //     return final_assignment;
        // }else{
        //     Assignment empty = new Assignment();
        //     return empty;
        // }
        return assignmentrepo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public void deleteAssignment(Integer id){
        assignmentrepo.deleteById(id);
    }
}
