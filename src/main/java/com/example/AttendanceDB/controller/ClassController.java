package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.ClassEntity;
import com.example.AttendanceDB.response.Response;
import com.example.AttendanceDB.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassController {

    @Autowired
    private ClassService service;

    // Post Mapping
    @PostMapping("/saveClass")
    public ResponseEntity<?> saveClass(@RequestBody ClassEntity classEntity){
        ClassEntity classEntity1 = service.saveClass(classEntity);

        if(classEntity1 == null){
            return new ResponseEntity<>(new Response("99", "Class does not exist"), HttpStatus.OK);
        } return new ResponseEntity<>(new Response("00", "Class saved successfully"), HttpStatus.CREATED);
    }

    // Get Mapping
    @GetMapping("/getAllClasses")
    public ResponseEntity<?> getAllClasses(){
        List<ClassEntity> classEntities = service.getAllClasses();

        if(classEntities.isEmpty()){
            return new ResponseEntity<>(new Response("99", "No class created"), HttpStatus.OK);
        } return new ResponseEntity<>(classEntities, HttpStatus.FOUND);
    }
}
