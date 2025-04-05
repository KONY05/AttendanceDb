package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Timetable;
import com.example.AttendanceDB.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
@RequiredArgsConstructor
public class TimetableController {

	private final TimetableService timetableService;

	@PostMapping
	public ResponseEntity<String> saveTimetable(@RequestBody Timetable timetable) {
		Timetable saved = timetableService.saveTimetable(timetable);
		return ResponseEntity.ok("Timetable saved with ID: " + saved.getTimetableId());
	}

	@GetMapping
	public List<Timetable> getAllTimetables() {
		return timetableService.getAllTimetables();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Timetable> getTimetableById(@PathVariable Integer id) {
		return timetableService.getTimetableById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateTimetable(@PathVariable Integer id, @RequestBody Timetable updatedTimetable) {
		Timetable updated = timetableService.updateTimetable(id, updatedTimetable);
		return ResponseEntity.ok("Timetable updated with ID: " + updated.getTimetableId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTimetable(@PathVariable Integer id) {
		timetableService.deleteTimetable(id);
		return ResponseEntity.ok("Timetable deleted successfully");
	}

	@GetMapping("/class/{classId}")
	public List<Timetable> getTimetablesByClassId(@PathVariable Integer classId) {
		return timetableService.getByClassId(classId);
	}
}
