package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Subject;
import com.example.AttendanceDB.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

	private final SubjectService subjectService;

	@PostMapping
	public ResponseEntity<String> saveSubject(@RequestBody Subject subject) {
		Subject saved = subjectService.saveSubject(subject);
		return ResponseEntity.ok("Subject saved with ID: " + saved.getSubjectId());
	}

	@GetMapping
	public ResponseEntity<List<Subject>> getAllSubjects() {
		return ResponseEntity.ok(subjectService.getAllSubjects());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
		return ResponseEntity.ok(subjectService.getSubjectById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateSubject(@PathVariable Integer id, @RequestBody Subject subject) {
		Subject updated = subjectService.updateSubject(id, subject);
		return ResponseEntity.ok("Subject updated with ID: " + updated.getSubjectId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteSubject(@PathVariable Integer id) {
		subjectService.deleteSubject(id);
		return ResponseEntity.ok("Subject deleted successfully");
	}
}
