package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Parent;
import com.example.AttendanceDB.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

	private final ParentService parentService;

	@PostMapping
	public ResponseEntity<String> saveParent(@RequestBody Parent parent) {
		Parent saved = parentService.saveParent(parent);
		return ResponseEntity.ok("Parent saved with ID: " + saved.getParentId());
	}

	@GetMapping
	public List<Parent> getAllParents() {
		return parentService.getAllParents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Parent> getParentById(@PathVariable Integer id) {
		return parentService.getParentById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateParent(@PathVariable Integer id, @RequestBody Parent updatedParent) {
		Parent updated = parentService.updateParent(id, updatedParent);
		return ResponseEntity.ok("Parent updated with ID: " + updated.getParentId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteParent(@PathVariable Integer id) {
		parentService.deleteParent(id);
		return ResponseEntity.ok("Parent deleted successfully");
	}
}
