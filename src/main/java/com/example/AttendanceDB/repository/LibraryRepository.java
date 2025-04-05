package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Library;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
	List<Library> findByStudent_Id(String studentId);

}
