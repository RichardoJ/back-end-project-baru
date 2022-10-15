package com.example.backendprojectbaru;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import com.example.backendprojectbaru.model.Student;
import com.example.backendprojectbaru.repository.StudentRepository;
import com.example.backendprojectbaru.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerUnitTest {

    @Mock
    private StudentRepository studentRepository;
    
    @InjectMocks
    private StudentService studentService;

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
        when(studentRepository.findAll()).thenReturn(listStudent);

        // Assert
        assertArrayEquals(arr, studentService.listAllStudent().toArray());
        
    }

     @Test
     void shouldGetOne(){
         // Mock Student
         Student studentOne = new Student(1, null, "michael", "bla@gmail.com", "1234", "pastoorstraat", 1);

         //Mock Service
         when(studentRepository.findById(1)).thenReturn(Optional.of(studentOne));

         // Assert
         assertEquals(studentOne, studentService.getStudent(1));
     }

    @Test
    void shouldNotGetOne(){
         //Mock Service
         when(studentRepository.findById(1)).thenReturn(Optional.empty());

         //Assert
         assertEquals(null, studentService.getStudent(1));
    }

    @Test
    void shouldSave(){
        Student studentOne = new Student(1, null, "michael", "bla@gmail.com", "1234", "pastoorstraat", 1);

        when(studentRepository.save(studentOne)).thenReturn(studentOne);

        assertEquals(studentOne, studentService.saveStudent(studentOne));
    }

    @Test
    void shouldDelete(){
        studentService.deleteStudent(1);
        assertEquals(null, studentService.getStudent(1));
    }





}
