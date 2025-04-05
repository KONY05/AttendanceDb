package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	// Optional<Attendance> findByStatus(String status);

	List<Attendance> findByStudent_Id(String studentId);

	List<Attendance> findAllByDate(LocalDate date);

	Optional<Attendance> findByStudent_IdAndDate(String studentId, LocalDate date);

}
