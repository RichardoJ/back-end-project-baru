package com.example.backendprojectbaru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import com.example.backendprojectbaru.model.Course;
import com.example.backendprojectbaru.model.Enrollment;
import com.example.backendprojectbaru.model.Student;
import com.example.backendprojectbaru.repository.EnrollmentRepository;
import com.example.backendprojectbaru.service.EnrollmentService;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceUnitTest {
    @Mock
    private EnrollmentRepository enrollmentRepository;
    @InjectMocks
    private EnrollmentService enrollmentService;
    
    @Test
    public void shouldGetAllEnrollment(){
        List<Enrollment> listEnrollment = new ArrayList<Enrollment>();
        Student student1 = new Student();
        Course course1 = new Course();
        Course course2 = new Course();
        Enrollment enroll1 = new Enrollment(1, student1, course1);
        Enrollment enroll2 = new Enrollment(2, student1, course2);
        listEnrollment.add(enroll1);
        listEnrollment.add(enroll2);

        when(enrollmentRepository.findAll()).thenReturn(listEnrollment);

        assertEquals(2, enrollmentService.listAllEnrollment().size());
    }

    @Test
    public void shouldGetOneEnrollment(){
        Student student1 = new Student();
        Course course1 = new Course();
        Enrollment enroll1 = new Enrollment(1, student1, course1);

        when(enrollmentRepository.findById(1)).thenReturn(Optional.of(enroll1));

        assertEquals(enroll1, enrollmentService.getEnrollment(1));
    }

    @Test
    void shouldDelete(){
        enrollmentRepository.deleteById(1);
        assertEquals(null, enrollmentService.getEnrollment(1));
    }
}
