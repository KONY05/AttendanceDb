package com.example.AttendanceDB.auth.controller;

import com.example.AttendanceDB.auth.model.AuthenticationRequest;
import com.example.AttendanceDB.auth.model.AuthenticationResponse;
import com.example.AttendanceDB.auth.model.RegisterRequest;
import com.example.AttendanceDB.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService authService;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
		AuthenticationResponse response = authService.register(request);
		return ResponseEntity.ok("User registered with ID: " + response.getUserId());
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
		return ResponseEntity.ok(authService.authenticate(request));
	}
}
