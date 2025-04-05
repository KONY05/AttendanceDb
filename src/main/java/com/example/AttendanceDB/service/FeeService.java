package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.FeePayment;
import com.example.AttendanceDB.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeeService {

	private final FeeRepository feeRepository;

	// Create a new fee record
	public FeePayment saveFee(FeePayment fee) {
		return feeRepository.save(fee);
	}

	// Get all fee records
	public List<FeePayment> getAllFees() {
		return feeRepository.findAll();
	}

	// Get a specific fee record by ID
	public Optional<FeePayment> getFeeById(Integer id) {
		return feeRepository.findById(id);
	}

	// Update a fee record
	public FeePayment updateFee(Integer id, FeePayment updatedFee) {
		return feeRepository.findById(id).map(fee -> {
			fee.setStudent(updatedFee.getStudent());
			fee.setAmountPaid(updatedFee.getAmountPaid());
			fee.setPaymentDate(updatedFee.getPaymentDate());
			fee.setPaymentStatus(updatedFee.getPaymentStatus());
			return feeRepository.save(fee);
		}).orElseThrow(() -> new RuntimeException("Fee record not found with ID: " + id));
	}

	// Delete a fee record
	public void deleteFee(Integer id) {
		feeRepository.deleteById(id);
	}

	// Get all fee records for a specific student
	public List<FeePayment> getFeesByStudentId(String studentId) {
		return feeRepository.findByStudent_Id(studentId);
	}
}
