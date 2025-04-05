package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Event;
import com.example.AttendanceDB.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

	private final EventRepository eventRepository;

	// Save a new event (check for duplicate by name and date)
	public Event saveEvent(Event event) {
		if (eventRepository.existsByEventNameAndDate(event.getEventName(), event.getDate())) {
			throw new RuntimeException("Event already exists on the given date: " + event.getEventName());
		}
		return eventRepository.save(event);
	}

	// Get all events
	public List<Event> getAllEvents() {
		return eventRepository.findAll();
	}

	// Get a specific event by ID
	public Optional<Event> getEventById(Integer id) {
		return eventRepository.findById(id);
	}

	// Update event
	public Event updateEvent(Integer id, Event updatedEvent) {
		return eventRepository.findById(id).map(event -> {
			event.setEventName(updatedEvent.getEventName());
			event.setDate(updatedEvent.getDate());
			event.setVenue(updatedEvent.getVenue());
			event.setDescription(updatedEvent.getDescription());
			return eventRepository.save(event);
		}).orElseThrow(() -> new RuntimeException("Event not found with ID: " + id));
	}

	// Delete event
	public void deleteEvent(Integer id) {
		eventRepository.deleteById(id);
	}
}
