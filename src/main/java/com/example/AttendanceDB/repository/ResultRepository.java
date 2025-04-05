package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
