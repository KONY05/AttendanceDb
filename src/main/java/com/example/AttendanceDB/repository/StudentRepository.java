package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
	Optional<Student> findById(String id);

	List<Student> findAllBySchoolClass_ClassName(String className);

	List<Student> findAllByParent_ParentId(Integer parentId);

	Optional<Student> findByEmail(String email);

}
