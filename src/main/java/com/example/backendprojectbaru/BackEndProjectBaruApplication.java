package com.example.backendprojectbaru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class BackEndProjectBaruApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndProjectBaruApplication.class, args);
	}

}
