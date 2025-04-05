package com.example.AttendanceDB.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	private String username;
	private String email;
	private String password;
	private Role role;

	// Student-specific field
	private String Id;

	// Common fields for student and teacher roles
	private String firstName;
	private String lastName;

	// Admin-specific field
	private String name;
}
