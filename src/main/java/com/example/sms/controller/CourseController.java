package com.example.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sms.dto.CourseDto;
import com.example.sms.service.courseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CourseController {
@Autowired
private courseService service;
    
@PostMapping("/course")
public  CourseDto    createCourse(@RequestBody CourseDto courseDto){

        
    return service.createCourse(courseDto);
    }

}
