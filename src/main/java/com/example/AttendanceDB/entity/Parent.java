package com.example.AttendanceDB.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer parentId;

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String address;

	@OneToMany(mappedBy = "parent")
	private List<Student> children;

}
