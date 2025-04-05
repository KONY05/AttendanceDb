package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Attendance;
import com.example.AttendanceDB.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
@RequiredArgsConstructor
public class AttendanceController {

	private final AttendanceService attendanceService;

	@PostMapping
	public ResponseEntity<String> saveAttendance(@RequestBody Attendance attendance) {
		Attendance saved = attendanceService.saveAttendance(attendance);
		return ResponseEntity.ok("Attendance saved with ID: " + saved.getAttendanceId());
	}

	@GetMapping
	public ResponseEntity<List<Attendance>> getAllAttendances() {
		return ResponseEntity.ok(attendanceService.getAllAttendances());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Attendance> getAttendanceById(@PathVariable Integer id) {
		return ResponseEntity.ok(attendanceService.getAttendanceById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateAttendance(@PathVariable Integer id, @RequestBody Attendance attendance) {
		Attendance updated = attendanceService.updateAttendance(id, attendance);
		return ResponseEntity.ok("Attendance updated with ID: " + updated.getAttendanceId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAttendance(@PathVariable Integer id) {
		attendanceService.deleteAttendance(id);
		return ResponseEntity.ok("Attendance deleted successfully");
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<Attendance>> getAttendanceByStudent(@PathVariable String studentId) {
		return ResponseEntity.ok(attendanceService.getAttendancesByStudentId(studentId));
	}
}
