package com.example.AttendanceDB.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resultId;

	@ManyToOne
	private Student student;

	@ManyToOne
	private Exam exam;

	@ManyToOne
	private Subject subject;

	private Double marksObtained;
	private String grade;
}
