package com.example.AttendanceDB.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "class_entity")
public class ClassEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer classId;

	private String className;
	private String section;

	@ManyToOne
	@JoinColumn(name = "class_teacher_id")
	private Teacher classTeacher;

	@OneToMany(mappedBy = "schoolClass")
	private List<Student> students;

	@OneToMany(mappedBy = "schoolClass")
	private List<Subject> subjects;

	@OneToMany(mappedBy = "schoolClass")
	private List<Timetable> timetableList;

}
