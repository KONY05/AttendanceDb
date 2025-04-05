package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.ClassEntity;
import com.example.AttendanceDB.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

	private ClassRepository classRepository;

	public ClassEntity saveClass(ClassEntity classEntity) {
		// Prevent duplicate by class name and section
		if (classRepository.findByClassNameAndSection(
				classEntity.getClassName(), classEntity.getSection()).isPresent()) {
			throw new IllegalArgumentException("Class with name " +
					classEntity.getClassName() + " and section " +
					classEntity.getSection() + " already exists.");
		}
		return classRepository.save(classEntity);
	}

	public List<ClassEntity> getAllClasses() {
		return classRepository.findAll();
	}

	public ClassEntity getClassById(Integer id) {
		return classRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Class with ID " + id + " not found."));
	}

	public ClassEntity updateClass(Integer id, ClassEntity updatedClass) {
		ClassEntity existing = getClassById(id);
		existing.setClassName(updatedClass.getClassName());
		existing.setSection(updatedClass.getSection());
		existing.setClassTeacher(updatedClass.getClassTeacher());
		return classRepository.save(existing);
	}

	public void deleteClass(Integer id) {
		if (!classRepository.existsById(id)) {
			throw new IllegalArgumentException("Cannot delete. Class with ID " + id + " does not exist.");
		}
		classRepository.deleteById(id);
	}
}
