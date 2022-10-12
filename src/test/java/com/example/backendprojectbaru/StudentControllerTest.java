package com.example.backendprojectbaru;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.backendprojectbaru.Controller.StudentController;
import com.example.backendprojectbaru.model.Student;
import com.example.backendprojectbaru.service.StudentService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = StudentController.class)
// @WithMockUser
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;
    Student mockStudent = new Student(1, null, "Michael", "Pinarto@gmail.com", "mantabjiwa", "jl. cendrawasih 165b, Makassar", 6);


    String exampleStudentJson = "{\"student_name\":\"Michael\",\"student_email\":\"Pinarto@gmail.com\",\"student_password\":\"mantabjiwa\",\"student_address\":\"jl. cendrawasih 165b, Makassar\"}";

    @Test
    public void retrieveDetailsForStudent() throws Exception{
        Mockito.when(studentService.getStudent(Mockito.anyInt())).thenReturn(mockStudent);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/student/1").accept(
				MediaType.APPLICATION_JSON);
        
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "{\"student_name\":\"Michael\",\"student_email\":\"Pinarto@gmail.com\",\"student_password\":\"mantabjiwa\"}";

        JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
    }
}
