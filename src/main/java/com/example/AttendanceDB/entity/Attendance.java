package com.example.AttendanceDB.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import com.example.AttendanceDB.enums.AttendanceStatus;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer attendanceId;

	private LocalDate date;

	@Enumerated(EnumType.STRING)
	private AttendanceStatus status; // Present/Absent

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity schoolClass;

}
