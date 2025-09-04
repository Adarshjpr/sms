package com.example.sms.dto;


import java.util.List;

// import com.example.sms.dto.CourseDto;

// import com.example.sms.model.Course;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor   
public class studentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private List<CourseDto> courses ;  



  public studentDto(Long id, String firstName, String lastName, String email, List<CourseDto> courses) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courses = courses;
    }



  public studentDto(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


}


// private List<CourseDto> course ;

//   public studentDto(Long id, String firstName, String lastName, String email, List<CourseDto> course) {
//     this.id = id;
//     this.firstName = firstName;
//     this.lastName = lastName;
//     this.email = email;
//     this.course = course;
// }