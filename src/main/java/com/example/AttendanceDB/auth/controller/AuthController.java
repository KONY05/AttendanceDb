package com.example.AttendanceDB.auth.controller;

import com.example.AttendanceDB.auth.model.AuthenticationResponse;
import com.example.AttendanceDB.auth.model.RegisterRequest;
import com.example.AttendanceDB.auth.service.AuthenticationService;
import com.example.AttendanceDB.auth.service.UserDetailsServiceImpl;
import com.example.AttendanceDB.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationService authService;
	private final AuthenticationManager authManager;
	private final UserDetailsServiceImpl userDetailsService;
	private final JwtUtil jwtUtil;

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
		AuthenticationResponse response = authService.register(request);
		return ResponseEntity.ok("User registered with ID: " + response.getUserId());
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(
			@RequestParam String username,
			@RequestParam String password) {
		var authToken = new UsernamePasswordAuthenticationToken(username, password);
		authManager.authenticate(authToken);

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(token);
	}
}
