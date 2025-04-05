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
public class Student {

	@Id
	private String id;

	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String address;
	private String phoneNumber;
	private String email;
	private String studentClass;
	private LocalDate enrollmentDate;

	@OneToOne
	private User user;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Parent parent;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity schoolClass;

	@OneToMany(mappedBy = "student")
	private List<Attendance> attendanceList;

	@OneToMany(mappedBy = "student")
	private List<Result> results;

	@OneToMany(mappedBy = "student")
	private List<FeePayment> payments;
}
