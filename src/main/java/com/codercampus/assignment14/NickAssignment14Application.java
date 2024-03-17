package com.codercampus.assignment14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.codercampus.assignment14.repository.MessageRepository;
import com.codercampus.assignment14.repository.MessageRepositoryImpl;

@SpringBootApplication
public class NickAssignment14Application {

	public static void main(String[] args) {
		SpringApplication.run(NickAssignment14Application.class, args);
	}
	
    

}
