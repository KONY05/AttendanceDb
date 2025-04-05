package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Parent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Integer> {

	Optional<Parent> findByPhoneNumber(String phoneNumber);

	Optional<Parent> findByEmail(String email);

	Optional<Parent> findByPhoneNumberOrEmail(String phoneNumber, String email);

}
