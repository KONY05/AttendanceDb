package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.FeePayment;
import com.example.AttendanceDB.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class FeeController {

	private final FeeService feeService;

	@PostMapping
	public ResponseEntity<String> saveFee(@RequestBody FeePayment payment) {
		FeePayment saved = feeService.saveFee(payment);
		return ResponseEntity.ok("Payment saved with ID: " + saved.getPaymentId());
	}

	@GetMapping
	public List<FeePayment> getAllFees() {
		return feeService.getAllFees();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FeePayment> getFeeById(@PathVariable Integer id) {
		return feeService.getFeeById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateFee(@PathVariable Integer id, @RequestBody FeePayment updatedPayment) {
		FeePayment updated = feeService.updateFee(id, updatedPayment);
		return ResponseEntity.ok("Payment updated with ID: " + updated.getPaymentId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePayment(@PathVariable Integer id) {
		feeService.deleteFee(id);
		return ResponseEntity.ok("Payment deleted successfully");
	}

	@GetMapping("/student/{studentId}")
	public List<FeePayment> getFeesByStudentId(@PathVariable String studentId) {
		return feeService.getFeesByStudentId(studentId);
	}
}
