package com.example.AttendanceDB.entity;

import com.example.AttendanceDB.auth.model.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminId;

	private String name;
	private String role; // Principal, Vice-Principal, etc.
	private String email;
	private String phoneNumber;

	@OneToOne
	private User user;
}
