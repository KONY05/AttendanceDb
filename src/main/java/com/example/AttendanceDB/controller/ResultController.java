package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Result;
import com.example.AttendanceDB.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {

	private final ResultService resultService;

	@PostMapping
	public ResponseEntity<String> saveResult(@RequestBody Result result) {
		Result saved = resultService.saveResult(result);
		return ResponseEntity.ok("Result saved with ID: " + saved.getResultId());
	}

	@GetMapping
	public List<Result> getAllResults() {
		return resultService.getAllResults();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Result> getResultById(@PathVariable Integer id) {
		return resultService.getResultById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateResult(@PathVariable Integer id, @RequestBody Result updatedResult) {
		Result updated = resultService.updateResult(id, updatedResult);
		return ResponseEntity.ok("Result updated with ID: " + updated.getResultId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteResult(@PathVariable Integer id) {
		resultService.deleteResult(id);
		return ResponseEntity.ok("Result deleted successfully");
	}

	@GetMapping("/student/{studentId}")
	public List<Result> getResultsByStudentId(@PathVariable String studentId) {
		return resultService.getResultsByStudentId(studentId);
	}
}
