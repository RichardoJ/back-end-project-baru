package com.example.backendprojectbaru.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backendprojectbaru.model.Assignment;
import com.example.backendprojectbaru.model.NotFoundException;
import com.example.backendprojectbaru.repository.AssignmentRepository;

@Service
@Transactional
public class AssignmentService {
    private final AssignmentRepository assignmentrepo;

    public AssignmentService(AssignmentRepository assignmentRepository){
        this.assignmentrepo = assignmentRepository;
    }

    public List<Assignment> listAllAssignment(){
        return assignmentrepo.findAll();
    }

    public Assignment saveAssignment(Assignment assignment_tmp){
        return assignmentrepo.save(assignment_tmp);
    }

    public Assignment getAssignment(Integer id) {
        Optional<Assignment> assignment = assignmentrepo.findById(id);
        if(assignment.isPresent()){
            return assignment.get();
        }else{
            return null;
        }
    }

    public void deleteAssignment(Integer id){
        assignmentrepo.deleteById(id);
    }
}
