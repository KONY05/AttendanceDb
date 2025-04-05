package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Subject;
import com.example.AttendanceDB.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

	private final SubjectRepository subjectRepository;

	public Subject saveSubject(Subject subject) {
		// Prevent duplicate subject codes
		if (subjectRepository.findBySubjectCode(subject.getSubjectCode()).isPresent()) {
			throw new IllegalArgumentException("Subject with code " + subject.getSubjectCode() + " already exists.");
		}
		return subjectRepository.save(subject);
	}

	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

	public Subject getSubjectById(Integer id) {
		return subjectRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Subject with ID " + id + " not found."));
	}

	public Subject updateSubject(Integer id, Subject updatedSubject) {
		Subject existing = getSubjectById(id);
		existing.setSubjectName(updatedSubject.getSubjectName());
		existing.setSubjectCode(updatedSubject.getSubjectCode());
		existing.setSchoolClass(updatedSubject.getSchoolClass());
		existing.setTeacher(updatedSubject.getTeacher());
		return subjectRepository.save(existing);
	}

	public void deleteSubject(Integer id) {
		if (!subjectRepository.existsById(id)) {
			throw new IllegalArgumentException("Cannot delete. Subject with ID " + id + " does not exist.");
		}
		subjectRepository.deleteById(id);
	}
}
