package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {

	Optional<ClassEntity> findByClassName(String className);

	List<ClassEntity> findByClassTeacher_TeacherId(Integer teacherId);

	Optional<ClassEntity> findByClassNameAndSection(String className, String section);

}
