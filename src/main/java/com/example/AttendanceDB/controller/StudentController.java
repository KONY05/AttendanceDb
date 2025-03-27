package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Student;
import com.example.AttendanceDB.response.Response;
import com.example.AttendanceDB.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    // Post mapping
    @PostMapping("/saveStudent")
    public ResponseEntity<?> saveStudent(@RequestBody Student student){
        Student student1 = service.saveStudent(student);

        if(student1 == null){
            return new ResponseEntity<>(new Response("99", "Student already exists"), HttpStatus.OK);
        } return new ResponseEntity<>(new Response("00", "Student saved succesffully"), HttpStatus.CREATED);
    }

    // Get mapping
    @GetMapping("/getStudent/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer studentId){
        Student student = service.getStudentById(studentId);

        if(student == null){
            return new ResponseEntity<>(new Response("99", "Student does not exist"), HttpStatus.OK);
        } return new ResponseEntity<>(student, HttpStatus.FOUND);
    }
}
