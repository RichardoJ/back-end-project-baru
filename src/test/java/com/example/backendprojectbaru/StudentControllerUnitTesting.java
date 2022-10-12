package com.example.backendprojectbaru;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.backendprojectbaru.model.Student;
import com.example.backendprojectbaru.repository.StudentRepository;
import com.example.backendprojectbaru.service.StudentService;

public class StudentControllerUnitTesting {

    @Test
    void shouldGetAll() {
        // Mock Student
        List<Student> listStudent = new ArrayList<Student>();
        Student studentOne = new Student(1, null, "michael", "bla@gmail.com", "1234", "pastoorstraat", 1);
        Student studentTwo = new Student(2, null, "michael", "bla@gmail.com", "1234", "pastoorstraat", 1);
        // Arrange
        listStudent.add(studentOne);
        listStudent.add(studentTwo);
        Object[] arr = listStudent.toArray();

        // Mock the Service
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        when(studentRepository.findAll()).thenReturn(listStudent);
        StudentService studentService = new StudentService(studentRepository);
        // when(studentService.listAllStudent()).thenReturn(listStudent);
        // StudentController studentController = new StudentController(studentService);

        // Assert
        assertArrayEquals(arr, studentService.listAllStudent().toArray());
    }

}
