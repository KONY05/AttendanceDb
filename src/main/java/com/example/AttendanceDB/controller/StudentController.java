package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Student;
import com.example.AttendanceDB.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@PostMapping
	public ResponseEntity<String> saveStudent(@RequestBody Student student) {
		Student savedStudent = studentService.saveStudent(student);
		return ResponseEntity.ok("Student saved with ID: " + savedStudent.getId());
	}

	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable String id) {
		return ResponseEntity.ok(studentService.getStudentById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable String id, @RequestBody Student student) {
		Student updated = studentService.updateStudent(id, student);
		return ResponseEntity.ok("Student updated with ID: " + updated.getId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable String id) {
		studentService.deleteStudent(id);
		return ResponseEntity.ok("Student deleted successfully");
	}
}
