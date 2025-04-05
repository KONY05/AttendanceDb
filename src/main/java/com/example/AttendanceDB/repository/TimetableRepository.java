package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Timetable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
	List<Timetable> findBySchoolClass_ClassId(Integer classId);

	List<Timetable> findAllByDayOfWeek(String dayOfWeek);

}
