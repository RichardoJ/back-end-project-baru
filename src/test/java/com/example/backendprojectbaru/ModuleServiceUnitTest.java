package com.example.backendprojectbaru;

import com.example.backendprojectbaru.model.Course;
import com.example.backendprojectbaru.model.Modules;
import com.example.backendprojectbaru.repository.ModulesRepository;
import com.example.backendprojectbaru.service.ModuleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModuleServiceUnitTest {

    @Mock
    private ModulesRepository modulesRepository;
    @InjectMocks
    private ModuleService moduleService;

    @Test
    public void shouldGetAllModules(){
        List<Modules> modules = new ArrayList<Modules>();
        Course course1 = new Course();
        Modules modules1 = new Modules(1,null, course1,"Modules 1", "0", "0","");
        Modules modules2 = new Modules(2,null, course1,"Modules 2", "0", "0","");

        modules.add(modules1);
        modules.add(modules2);
        Object[] arr = modules.toArray();

        when(modulesRepository.findAll()).thenReturn(modules);

        assertArrayEquals(arr,moduleService.listAllModules().toArray());
    }

    @Test
    public void shouldGetOneModule(){
        Course course1 = new Course();
        Modules modules1 = new Modules(1,null, course1,"Modules 1", "0", "0","");

        when(modulesRepository.findById(1)).thenReturn(Optional.of(modules1));

        assertEquals(modules1, moduleService.getModule(1));
    }

    @Test
    public void shouldNotGetModule(){

        when(modulesRepository.findById(1)).thenReturn(Optional.empty());

        assertEquals(null,moduleService.getModule(1));
    }

    @Test
    void shouldDelete(){
        modulesRepository.deleteById(1);
        assertEquals(null, moduleService.getModule(1));
    }
}
