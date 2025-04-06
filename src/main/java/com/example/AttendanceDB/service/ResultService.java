package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Result;
import com.example.AttendanceDB.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultService {

	private final ResultRepository resultRepository;

	// Create with safeguard: prevent duplicate result for same student, exam, and
	// subject
	public Result saveResult(Result result) {
		Optional<Result> existingResult = resultRepository.findByStudent_IdAndExam_ExamIdAndSubject_SubjectId(
				result.getStudent().getId(), result.getExam().getExamId(), result.getSubject().getSubjectId());

		if (existingResult.isPresent()) {
			throw new RuntimeException("Result already exists for this student, exam, and subject.");
		}
		return resultRepository.save(result);
	}

	// Get all results
	public List<Result> getAllResults() {
		return resultRepository.findAll();
	}

	// Get by ID
	public Optional<Result> getResultById(Integer id) {
		return resultRepository.findById(id);
	}

	// Update result
	public Result updateResult(Integer id, Result updatedResult) {
		return resultRepository.findById(id).map(result -> {
			result.setMarksObtained(updatedResult.getMarksObtained());
			result.setGrade(updatedResult.getGrade());
			result.setStudent(updatedResult.getStudent());
			result.setExam(updatedResult.getExam());
			result.setSubject(updatedResult.getSubject());
			return resultRepository.save(result);
		}).orElseThrow(() -> new RuntimeException("Result not found with ID: " + id));
	}

	// Delete result
	public void deleteResult(Integer id) {
		resultRepository.deleteById(id);
	}

	// Custom: Find results by student ID
	public List<Result> getResultsByStudentId(String studentId) {
		return resultRepository.findByStudent_Id(studentId);
	}

	// Custom: Find results by exam ID
	public List<Result> getResultsByExamId(Integer examId) {
		return resultRepository.findByExam_Id(examId);
	}
}
