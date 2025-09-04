package com.example.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sms.dto.CourseDto;
import com.example.sms.model.Course;
import com.example.sms.repositry.CourseRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class courseService {

    @Autowired
    private CourseRepo repo;


    public CourseDto createCourse(CourseDto courseDto) {
  
      Course cmodel =  Course.builder().courseName(courseDto.getCourseName()).build();
        
repo.save(cmodel);

 CourseDto cDto =  new CourseDto();
 cDto.setCourseName(cmodel.getCourseName());
 cDto.setId(cmodel.getId());

        
        return cDto;

    }

    // get by alll  => ye apke kmm h 


    
}
