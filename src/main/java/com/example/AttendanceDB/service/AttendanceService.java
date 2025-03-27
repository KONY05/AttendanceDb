package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Attendance;
import com.example.AttendanceDB.entity.Student;
import com.example.AttendanceDB.enums.AttendanceStatus;
import com.example.AttendanceDB.repository.AttendanceRepository;
import com.example.AttendanceDB.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private StudentRepository studentRepo;

    public Attendance saveAttendance(Attendance attendance){
        Attendance attendance1 = attendanceRepo.findByDate(attendance.getDate()).orElse(null);

        if(attendance1 == null){
            attendance.setDate(new Date());
            attendance.setStatus(AttendanceStatus.PRESENT);
            return attendanceRepo.save(attendance);
        }else{
            return null;
        }
    }

    public List<Attendance> getStudentAttendance (Student studentId){
        Student student = studentRepo.findById(studentId.getId()).orElse(null);

        if(student != null) {
            List<Attendance> attendance = attendanceRepo.findByStudent(student);
            return attendance;
        }else{
            return null;
        }
    }

    public List<Attendance> getAllAttendance (){
       return attendanceRepo.findAll();
    }
}
