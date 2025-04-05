package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Teacher;
import com.example.AttendanceDB.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

	private final TeacherService teacherService;

	@PostMapping
	public ResponseEntity<String> saveTeacher(@RequestBody Teacher teacher) {
		Teacher saved = teacherService.saveTeacher(teacher);
		return ResponseEntity.ok("Teacher saved with ID: " + saved.getTeacherId());
	}

	@GetMapping
	public ResponseEntity<List<Teacher>> getAllTeachers() {
		return ResponseEntity.ok(teacherService.getAllTeachers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer id) {
		return ResponseEntity.ok(teacherService.getTeacherById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateTeacher(@PathVariable Integer id, @RequestBody Teacher teacher) {
		Teacher updated = teacherService.updateTeacher(id, teacher);
		return ResponseEntity.ok("Teacher updated with ID: " + updated.getTeacherId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTeacher(@PathVariable Integer id) {
		teacherService.deleteTeacher(id);
		return ResponseEntity.ok("Teacher deleted successfully");
	}
}
