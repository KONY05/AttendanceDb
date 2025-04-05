package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Teacher;
import com.example.AttendanceDB.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

	private final TeacherRepository teacherRepository;

	public List<Teacher> getAllTeachers() {
		return teacherRepository.findAll();
	}

	public Teacher getTeacherById(Integer id) {
		return teacherRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Teacher with ID " + id + " not found."));
	}

	public Teacher saveTeacher(Teacher teacher) {
		// Prevent duplicate teacher by email
		if (teacherRepository.findByEmail(teacher.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Teacher with email " + teacher.getEmail() + " already exists.");
		}
		return teacherRepository.save(teacher);
	}

	public void deleteTeacher(Integer id) {
		if (!teacherRepository.existsById(id)) {
			throw new IllegalArgumentException("Cannot delete. Teacher with ID " + id + " does not exist.");
		}
		teacherRepository.deleteById(id);
	}

	public Teacher updateTeacher(Integer id, Teacher updatedTeacher) {
		Teacher existing = getTeacherById(id);
		existing.setFirstName(updatedTeacher.getFirstName());
		existing.setLastName(updatedTeacher.getLastName());
		existing.setGender(updatedTeacher.getGender());
		existing.setAddress(updatedTeacher.getAddress());
		existing.setPhoneNumber(updatedTeacher.getPhoneNumber());
		existing.setEmail(updatedTeacher.getEmail());
		existing.setSubjectTaught(updatedTeacher.getSubjectTaught());
		existing.setQualification(updatedTeacher.getQualification());
		existing.setHireDate(updatedTeacher.getHireDate());
		return teacherRepository.save(existing);
	}
}
