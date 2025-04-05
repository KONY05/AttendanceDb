package com.example.AttendanceDB.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Timetable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer timetableId;

	private String dayOfWeek;
	private Integer periodNumber;

	@ManyToOne
	private Subject subject;

	@ManyToOne
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity schoolClass;

}
