package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Timetable;
import com.example.AttendanceDB.repository.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimetableService {

	private final TimetableRepository timetableRepository;

	// Save a new timetable entry
	public Timetable saveTimetable(Timetable timetable) {
		return timetableRepository.save(timetable);
	}

	// Get all timetable entries
	public List<Timetable> getAllTimetables() {
		return timetableRepository.findAll();
	}

	// Get a specific timetable by ID
	public Optional<Timetable> getTimetableById(Integer id) {
		return timetableRepository.findById(id);
	}

	// Update a timetable entry
	public Timetable updateTimetable(Integer id, Timetable updatedTimetable) {
		return timetableRepository.findById(id).map(timetable -> {
			timetable.setSchoolClass(updatedTimetable.getSchoolClass());
			timetable.setDayOfWeek(updatedTimetable.getDayOfWeek());
			timetable.setPeriodNumber(updatedTimetable.getPeriodNumber());
			timetable.setSubject(updatedTimetable.getSubject());
			timetable.setTeacher(updatedTimetable.getTeacher());
			return timetableRepository.save(timetable);
		}).orElseThrow(() -> new RuntimeException("Timetable not found with ID: " + id));
	}

	// Delete a timetable entry
	public void deleteTimetable(Integer id) {
		timetableRepository.deleteById(id);
	}

	// Get timetable by class ID
	public List<Timetable> getByClassId(Integer classId) {
		return timetableRepository.findBySchoolClass_ClassId(classId);
	}
}
