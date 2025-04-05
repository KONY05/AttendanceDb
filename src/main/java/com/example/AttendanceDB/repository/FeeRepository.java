package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.FeePayment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeRepository extends JpaRepository<FeePayment, Integer> {
	List<FeePayment> findByStudent_Id(String studentId);

	List<FeePayment> findAllByPaymentStatus(String paymentStatus);

}
