package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Optional<Admin> findByEmail(String email);

	boolean existsByEmail(String email);

}
