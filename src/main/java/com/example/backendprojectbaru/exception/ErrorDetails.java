package com.example.backendprojectbaru.exception;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    
    private LocalDate timeStamp;
    private String message;
    private String details;
}
