package com.example.AttendanceDB.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subjectId;

	private String subjectName;
	private String subjectCode;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity schoolClass;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@OneToMany(mappedBy = "subject")
	private List<Result> results;

	@OneToMany(mappedBy = "subject")
	private List<Timetable> timetableEntries;

}
