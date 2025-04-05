package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.ClassEntity;
import com.example.AttendanceDB.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {

	private final ClassService classService;

	@PostMapping
	public ResponseEntity<String> saveClass(@RequestBody ClassEntity classEntity) {
		ClassEntity saved = classService.saveClass(classEntity);
		return ResponseEntity.ok("Class saved with ID: " + saved.getClassId());
	}

	@GetMapping
	public ResponseEntity<List<ClassEntity>> getAllClasses() {
		return ResponseEntity.ok(classService.getAllClasses());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassEntity> getClassById(@PathVariable Integer id) {
		return ResponseEntity.ok(classService.getClassById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateClass(@PathVariable Integer id, @RequestBody ClassEntity classEntity) {
		ClassEntity updated = classService.updateClass(id, classEntity);
		return ResponseEntity.ok("Class updated with ID: " + updated.getClassId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteClass(@PathVariable Integer id) {
		classService.deleteClass(id);
		return ResponseEntity.ok("Class deleted successfully");
	}
}
