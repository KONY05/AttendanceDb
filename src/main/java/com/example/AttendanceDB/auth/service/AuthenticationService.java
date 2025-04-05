package com.example.AttendanceDB.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.AttendanceDB.auth.model.AuthenticationRequest;
import com.example.AttendanceDB.auth.model.AuthenticationResponse;
import com.example.AttendanceDB.auth.model.RegisterRequest;
import com.example.AttendanceDB.auth.model.User;
import com.example.AttendanceDB.auth.repository.UserRepository;
import com.example.AttendanceDB.auth.util.JwtUtil;
import com.example.AttendanceDB.entity.Admin;
import com.example.AttendanceDB.entity.Student;
import com.example.AttendanceDB.entity.Teacher;
import com.example.AttendanceDB.service.AdminService;
import com.example.AttendanceDB.service.StudentService;
import com.example.AttendanceDB.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;

	private final StudentService studentService;
	private final TeacherService teacherService;
	private final AdminService adminService;

	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole())
				.build();

		userRepository.save(user);

		switch (request.getRole()) {
			case STUDENT -> {
				Student student = Student.builder()
						.id(request.getId()) // comes from frontend
						.firstName(request.getFirstName())
						.lastName(request.getLastName())
						.user(user)
						.build();
				student.setUser(user);
				studentService.saveStudent(student);
			}
			case TEACHER -> {
				Teacher teacher = Teacher.builder()
						.firstName(request.getFirstName())
						.lastName(request.getLastName())
						.user(user)
						.build();
				teacher.setUser(user);
				teacherService.saveTeacher(teacher);
			}
			case ADMIN -> {
				Admin admin = Admin.builder()
						.name(request.getFirstName())
						.user(user)
						.build();
				admin.setUser(user);
				adminService.saveAdmin(admin);
			}
		}

		var jwtToken = jwtUtil.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.username(user.getUsername())
				.role(user.getRole().name())
				.userId(user.getId())
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()));

		var user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		var jwtToken = jwtUtil.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.username(user.getUsername())
				.role(user.getRole().name())
				.build();
	}
}
