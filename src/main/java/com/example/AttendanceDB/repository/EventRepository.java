package com.example.AttendanceDB.repository;

import com.example.AttendanceDB.entity.Event;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
	List<Event> findAllByDate(LocalDate date);

	List<Event> findAllByVenue(String venue);

	boolean existsByEventNameAndDate(String eventName, LocalDate date);

}
