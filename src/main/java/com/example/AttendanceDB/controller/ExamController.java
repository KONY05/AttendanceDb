package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Exam;
import com.example.AttendanceDB.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

	private final ExamService examService;

	@PostMapping
	public ResponseEntity<String> saveExam(@RequestBody Exam exam) {
		Exam saved = examService.saveExam(exam);
		return ResponseEntity.ok("Exam saved with ID: " + saved.getExamId());
	}

	@GetMapping
	public ResponseEntity<List<Exam>> getAllExams() {
		return ResponseEntity.ok(examService.getAllExams());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Exam> getExamById(@PathVariable Integer id) {
		return examService.getExamById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateExam(@PathVariable Integer id, @RequestBody Exam exam) {
		Exam updated = examService.updateExam(id, exam);
		return ResponseEntity.ok("Exam updated with ID: " + updated.getExamId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteExam(@PathVariable Integer id) {
		examService.deleteExam(id);
		return ResponseEntity.ok("Exam deleted successfully");
	}
}
