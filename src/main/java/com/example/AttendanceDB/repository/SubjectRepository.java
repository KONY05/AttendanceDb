package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Subject;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	List<Subject> findAllBySchoolClass_ClassId(Integer classId);

	List<Subject> findAllByTeacher_TeacherId(Integer teacherId);

	Optional<Subject> findBySubjectCode(String subjectCode);

}
