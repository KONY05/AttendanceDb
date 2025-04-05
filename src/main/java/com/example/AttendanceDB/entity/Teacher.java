package com.example.AttendanceDB.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.example.AttendanceDB.auth.model.User;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teacherId;

	private String firstName;
	private String lastName;
	private String gender;
	private String address;
	private String phoneNumber;
	private String email;
	private String subjectTaught;
	private String qualification;
	private LocalDate hireDate;

	@OneToOne
	private User user;

	@OneToMany(mappedBy = "teacher")
	private List<Subject> subjects;

	@OneToMany(mappedBy = "classTeacher")
	private List<ClassEntity> classes;

	@OneToMany(mappedBy = "teacher")
	private List<Timetable> timetables;
}
