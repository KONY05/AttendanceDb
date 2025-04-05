package com.example.AttendanceDB.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer libraryId;

	private String title;
	private String author;
	private String isbn;

	private LocalDate issueDate;
	private LocalDate returnDate;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

}
