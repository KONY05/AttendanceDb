package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Exam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
	List<Exam> findAllByExamType(String examType);

	Optional<Exam> findByExamTypeAndDateAndSchoolClass_ClassId(String examType, LocalDate date, Integer classId);

	List<Exam> findBySchoolClass_ClassId(Integer classId);

}
