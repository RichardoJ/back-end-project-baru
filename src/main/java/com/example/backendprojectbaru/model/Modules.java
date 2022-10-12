package com.example.backendprojectbaru.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedNativeQuery(name = "Modules.findAllAssignmentPerModule", query = "select a.assignment_name as name, a.assignment_score as score, a.assignment_status as status, m.id as mid from Assignment as a JOIN modules m ON m.id = a.module_id", resultSetMapping = "Mapping.Assignments")
@SqlResultSetMapping(name = "Mapping.Assignments", classes = @ConstructorResult(targetClass = Assignment.class, columns = {@ColumnResult(name = "name"), @ColumnResult(name = "score"), @ColumnResult(name = "status"), @ColumnResult(name = "mid")}))

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "modules")
public class Modules implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "module", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Assignment> assignments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Course course_module;
    
    private String modules_name;
    private String modules_score;
    private String modules_progress;

    
}
