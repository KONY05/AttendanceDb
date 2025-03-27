package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Attendance;
import com.example.AttendanceDB.entity.Student;
import com.example.AttendanceDB.response.Response;
import com.example.AttendanceDB.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    // Post mapping
    @PostMapping("/takeAttendance")
    public ResponseEntity<?> saveAttendance(@RequestBody Attendance attendance){
        Attendance attendance1 = service.saveAttendance(attendance);

        if(attendance1 == null){
            return new ResponseEntity<>(new Response("99", "Attendance already taken"), HttpStatus.OK);
        } return new ResponseEntity<>(new Response("00", "Attendance taken successfully"), HttpStatus.CREATED);
    }

    // Get mapping
    @GetMapping("/getStudentAttendance/{studentId}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Student studentId){
        List<Attendance> attendance = service.getStudentAttendance(studentId);

        if(attendance == null){
            return new ResponseEntity<>(new Response("99", "Student does not exist"), HttpStatus.OK);
        } return new ResponseEntity<>(attendance, HttpStatus.FOUND);
    }

    @GetMapping("/getAllAttendance")
    public ResponseEntity<?> getAllAttendance(){
        List<Attendance> attendance = service.getAllAttendance();

        if(attendance.isEmpty()){
            return new ResponseEntity<>(new Response("99", "Attendance is empty"), HttpStatus.OK);
        }return new ResponseEntity<>(attendance, HttpStatus.FOUND);
    }
}
