package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Teacher;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	Optional<Teacher> findByEmail(String email);

	List<Teacher> findAllByQualification(String qualification);

	Optional<Teacher> findById(Integer id);

}
