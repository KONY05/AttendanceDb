package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Result;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Integer> {

	Optional<Result> findByStudent_IdAndExam_ExamIdAndSubject_SubjectId(String studentId, Integer examId,
			Integer subjectId);

	List<Result> findByStudent_Id(String studentId);

	List<Result> findByExam_Id(Integer examId);

}
