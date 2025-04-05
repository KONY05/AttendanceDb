package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Admin;
import com.example.AttendanceDB.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService;

	@PostMapping
	public ResponseEntity<String> saveAdmin(@RequestBody Admin admin) {
		Admin saved = adminService.saveAdmin(admin);
		return ResponseEntity.ok("Admin saved with ID: " + saved.getAdminId());
	}

	@GetMapping
	public List<Admin> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) {
		return adminService.getAdminById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateAdmin(@PathVariable Integer id, @RequestBody Admin updatedAdmin) {
		Admin updated = adminService.updateAdmin(id, updatedAdmin);
		return ResponseEntity.ok("Admin updated with ID: " + updated.getAdminId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAdmin(@PathVariable Integer id) {
		adminService.deleteAdmin(id);
		return ResponseEntity.ok("Admin deleted successfully");
	}
}
