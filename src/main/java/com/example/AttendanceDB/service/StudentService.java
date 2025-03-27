package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Student;
import com.example.AttendanceDB.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public Student saveStudent(Student student){
        Student student1 = studentRepo.findByMatNo(student.getMatNo()).orElse(null);

        if(student1 == null){
            return studentRepo.save(student);
        }else{
            return null;
        }
    }

    public Student getStudentById(Integer studentId){
        Student student = studentRepo.findById(studentId).orElse(null);

        if(student != null){
            return student;
        }else{
            return null;
        }
    }
}
