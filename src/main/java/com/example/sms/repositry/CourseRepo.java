package com.example.sms.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sms.model.Course;

public interface CourseRepo extends JpaRepository<Course , Long> {


    
} 
