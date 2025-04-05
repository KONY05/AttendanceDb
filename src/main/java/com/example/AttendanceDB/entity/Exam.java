package com.example.AttendanceDB.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer examId;

	private String examType;
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private ClassEntity schoolClass;

	@OneToMany(mappedBy = "exam")
	private List<Result> results;

}
