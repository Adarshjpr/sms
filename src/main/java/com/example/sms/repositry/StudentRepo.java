package com.example.sms.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sms.model.Student;

public interface StudentRepo extends JpaRepository<Student ,Long> {

    
} 
