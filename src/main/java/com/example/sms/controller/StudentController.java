package com.example.sms.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.sms.dto.studentDto;

import com.example.sms.service.studentService;

import lombok.RequiredArgsConstructor;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

@Autowired
    private studentService service;

@PostMapping()
public studentDto createStudent(@RequestBody studentDto sDto) {
    
    
    return service.createStudent(sDto);
}

//  join to course

@PostMapping("/{studentId}/course/{cousreId}")
public studentDto jointoCourse(@PathVariable Long studentId, @PathVariable Long cousreId )

{

return service.jointoCourse(studentId,cousreId);

}


// =====================  upload api =====================


@PostMapping("/upload")
public String Uploade(@RequestParam("file") MultipartFile file) {
  
 if(file.isEmpty()){
    return "file is empty";
 }



    // return " file name  :  =" + file.getOriginalFilename() ;
// Count  meri file me se kitne data inster hua h ye bana dega
//   50 => 40 
 int Count  = service.importStudentCsv(file); //100


return "uploaded  " + Count + "student successfully ";

}




}

