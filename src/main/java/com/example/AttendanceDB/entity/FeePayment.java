package com.example.AttendanceDB.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeePayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;

	private Double amountPaid;
	private LocalDate paymentDate;
	private String paymentStatus; // Paid / Pending

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;

}
