package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Event;
import com.example.AttendanceDB.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

	private final EventService eventService;

	@PostMapping
	public ResponseEntity<String> saveEvent(@RequestBody Event event) {
		Event saved = eventService.saveEvent(event);
		return ResponseEntity.ok("Event saved with ID: " + saved.getEventId());
	}

	@GetMapping
	public List<Event> getAllEvents() {
		return eventService.getAllEvents();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
		return eventService.getEventById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateEvent(@PathVariable Integer id, @RequestBody Event updatedEvent) {
		Event updated = eventService.updateEvent(id, updatedEvent);
		return ResponseEntity.ok("Event updated with ID: " + updated.getEventId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {
		eventService.deleteEvent(id);
		return ResponseEntity.ok("Event deleted successfully");
	}
}
