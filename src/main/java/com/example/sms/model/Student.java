package com.example.sms.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;  // unqiue value 




    @OneToMany(mappedBy = "student" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses =  new ArrayList<>();


    // courses.add("mango")


    // public List<Course> getCourses() {
    //   return courses;
    // }





 


}
