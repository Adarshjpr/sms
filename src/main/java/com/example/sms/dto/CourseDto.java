package com.example.sms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  
public class CourseDto {
    private Long id;
    private String courseName;
    public CourseDto(Long id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

}
