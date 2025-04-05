package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Attendance;
import com.example.AttendanceDB.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	public Attendance saveAttendance(Attendance attendance) {
		// Prevent duplicate attendance entry for the same student on the same date
		boolean exists = attendanceRepository
				.findByStudent_IdAndDate(attendance.getStudent().getId(), attendance.getDate())
				.isPresent();

		if (exists) {
			throw new IllegalArgumentException("Attendance for student " + attendance.getStudent().getId()
					+ " on " + attendance.getDate() + " already exists.");
		}

		return attendanceRepository.save(attendance);
	}

	public List<Attendance> getAttendancesByStudentId(String studentId) {
		return attendanceRepository.findByStudent_Id(studentId);
	}

	public List<Attendance> getAllAttendances() {
		return attendanceRepository.findAll();
	}

	public Attendance getAttendanceById(Integer id) {
		return attendanceRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Attendance with ID " + id + " not found."));
	}

	public Attendance updateAttendance(Integer id, Attendance updated) {
		Attendance existing = getAttendanceById(id);
		existing.setStudent(updated.getStudent());
		existing.setSchoolClass(updated.getSchoolClass());
		existing.setDate(updated.getDate());
		existing.setStatus(updated.getStatus());
		return attendanceRepository.save(existing);
	}

	public void deleteAttendance(Integer id) {
		if (!attendanceRepository.existsById(id)) {
			throw new IllegalArgumentException("Cannot delete. Attendance with ID " + id + " does not exist.");
		}
		attendanceRepository.deleteById(id);
	}
}
