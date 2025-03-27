package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Attendance;
import com.example.AttendanceDB.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    //Optional<Attendance> findByStatus(String status);

    List<Attendance> findByStudent(Student student);

    Optional<Attendance> findByDate(Date date);
}
