package com.example.sms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.sms.dto.CourseDto;
// import com.example.sms.dto.CourseDto;
import com.example.sms.dto.studentDto;
import com.example.sms.model.Course;
import com.example.sms.model.Student;
import com.example.sms.repositry.CourseRepo;
import com.example.sms.repositry.StudentRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class studentService {

@Autowired
private StudentRepo repo;
@Autowired
private CourseRepo courseRepo;

    public studentDto createStudent(studentDto sDto) {
   
Student smodel =  Student.builder()
.firstName(sDto.getFirstName())
.lastName(sDto.getLastName())
.email(sDto.getEmail())
.build();

repo.save(smodel);

studentDto SDTOS = new studentDto(smodel.getId(),smodel.getFirstName(), smodel.getLastName(), smodel.getEmail());



 return SDTOS;

    }

    public studentDto jointoCourse(Long studentId, Long cousreId) {
   Student student=    repo.findById(studentId).orElseThrow(()-> new RuntimeException(" student id not found"));
    Course course =  courseRepo.findById(cousreId).orElseThrow(()-> new RuntimeException(" course id not found"));


    student.getCourses().add(course);  // ye line likhte hi  list bale cousre jo h mere student me waha me ye core de jo data find hus h woo push ho jaye  


    repo.save(student);

 // yaha tak pe clear the mamala database me 



   return  new studentDto(     // ye reponse likh ke liye likhi 
    student.getId(),student.getFirstName()
    ,student.getLastName(),student.getEmail()
    , student.getCourses().stream().map(c -> new CourseDto(c.getId(),c.getCourseName())  ).toList()
    
    
    );
    }

 


//  feture vitrual thread  =>  csv file uppolder  


//  today  

//  ONE to many   ||  many to one 
//  Normalization  => https://www.geeksforgeeks.org/dbms/introduction-of-database-normalization/

//    
  
// ======================================= 1000 csv file upload ======================================




@Transactional
   public int importStudentCsv(MultipartFile file) {
   
    //  ye unversal array 
    List <Student> students = new ArrayList<>();
    
   
    /*Buffer READER 
     *  iNPUTSTREAM   
     * inputstreamReader
     * 
     *      getInputStream =>      eske kmm h file ko memory se lana (1100010101001 (bYTE))
     *      InputStreamReader  =>        BYTE CODE TO CONVERT KARNE  HU
     *    1100101010010001010101 (GetInputStream) =>  " ADARSH  ,UNCODEMY  , ADARSH@EXAMPLE.COM , JAVA|REACT|MERN" 
     *                                                 " Anuj  ,UNCODEMY  , Aunj@EXAMPLE.COM , JAVA|REACT|",
     *                                                 " Ajay  ,UNCODEMY  , Ajay@EXAMPLE.COM , |REACT|MERN",
     *                                                   "                                                "  
     *  @transtional => ACID =>  jaha kuch galat wapas le aoo  uske or uske aage tak ke data 
     */
      
     try {
        //  file se data lao 
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
 String line ;
//  haar ak line check karo 
while ((line= reader.readLine())!=null) {
//  STEP  1  LINE  KO   
    // Line     =>     " ADARSH  ,UNCODEMY  , ADARSH@EXAMPLE.COM , JAVA|REACT|MERN" 
            String[] part  =        line.split(",");

// {" ADARSH"   ,"UNCODEMY"  , "ADARSH@EXAMPLE.COM" , "JAVA|REACT|MERN" }
//     0,          1              , 2                ,  3


String Fs =part[0].trim(); 
String Ls = part[1].trim();
String email = part[2].trim();



Student student = new Student();

student.setFirstName(Fs);
student.setLastName(Ls);
student.setEmail(email);
 
// multiple
//  "JAVA|REACT|MERN" 

// {index  0 = java }
// {index 1 = react }
// { index 2 = MERN}
 String [] CourseNames = part[3].trim().split("\\|") ;



for(String CourseName : CourseNames){

    
    Course course = new Course();

    course.setCourseName(CourseName.trim());


    //  two line

    course.setStudent(student);  //  => parent verify

     student.getCourses().add(course); //=>  te line 
}


//  yaha tak me apne data ko modal tak le lke ja rha hu 
//  model se database 

repo.saveAll(students);

students.add(student);
}









     } catch (IOException e) {
        e.printStackTrace();
     }



    return  students.size();
    }

























}











































//     public studentDto joinTOcourse(Long studentId, Long cousreId) {
  
// Student student = repo.findById(studentId).orElseThrow(()-> new RuntimeException("\"studentnot found\"")) ;
 
// Course course = courseRepo.findById(cousreId).orElseThrow(()-> new RuntimeException("course  id not foulnd "));


// student.getCourse().add(course);

// repo.save(student);




//  return  new studentDto( student.getId(),student.getFirstName(),student.getLastName(),student.getEmail(), student.getCourse().stream().map(c -> new CourseDto(c.getId(),c.getCourseName())).toList());



//     }