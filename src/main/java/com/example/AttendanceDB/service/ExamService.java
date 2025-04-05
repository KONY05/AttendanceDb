package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Exam;
import com.example.AttendanceDB.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {

	private final ExamRepository examRepository;

	// Create Exam with safeguard (prevent same type & date & classId)
	public Exam saveExam(Exam exam) {
		Optional<Exam> existingExam = examRepository.findByExamTypeAndDateAndSchoolClass_ClassId(
				exam.getExamType(), exam.getDate(), exam.getSchoolClass().getClassId());
		if (existingExam.isPresent()) {
			throw new RuntimeException("An exam of this type already exists for the class on that date.");
		}
		return examRepository.save(exam);
	}

	// Get all exams
	public List<Exam> getAllExams() {
		return examRepository.findAll();
	}

	// Get by ID
	public Optional<Exam> getExamById(Integer id) {
		return examRepository.findById(id);
	}

	// Update
	public Exam updateExam(Integer id, Exam updatedExam) {
		return examRepository.findById(id).map(exam -> {
			exam.setExamType(updatedExam.getExamType());
			exam.setDate(updatedExam.getDate());
			exam.setSchoolClass(updatedExam.getSchoolClass());
			return examRepository.save(exam);
		}).orElseThrow(() -> new RuntimeException("Exam not found with ID: " + id));
	}

	// Delete
	public void deleteExam(Integer id) {
		examRepository.deleteById(id);
	}

	// Custom: Get exams by class
	public List<Exam> getExamsByClassId(Integer classId) {
		return examRepository.findBySchoolClass_ClassId(classId);
	}
}
