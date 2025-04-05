package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Student;
import com.example.AttendanceDB.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		if (studentRepository.existsById(student.getId())) {
			throw new IllegalArgumentException("Student with ID " + student.getId() + " already exists.");
		}
		return studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student getStudentById(String id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Student with ID " + id + " not found."));
	}

	public Student updateStudent(String id, Student updatedStudent) {
		return studentRepository.findById(id).map(student -> {
			student.setFirstName(updatedStudent.getFirstName());
			student.setLastName(updatedStudent.getLastName());
			student.setDateOfBirth(updatedStudent.getDateOfBirth());
			student.setGender(updatedStudent.getGender());
			student.setAddress(updatedStudent.getAddress());
			student.setPhoneNumber(updatedStudent.getPhoneNumber());
			student.setEmail(updatedStudent.getEmail());
			student.setStudentClass(updatedStudent.getStudentClass());
			student.setEnrollmentDate(updatedStudent.getEnrollmentDate());
			student.setParent(updatedStudent.getParent());
			return studentRepository.save(student);
		}).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
	}

	public void deleteStudent(String id) {
		if (!studentRepository.existsById(id)) {
			throw new IllegalArgumentException("Cannot delete. Student with ID " + id + " does not exist.");
		}
		studentRepository.deleteById(id);
	}
}
