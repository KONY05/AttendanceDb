package com.example.AttendanceDB.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

	@GetMapping("/protected")
	public ResponseEntity<String> protectedEndpoint() {
		return ResponseEntity.ok("✅ You're authenticated and JWT is valid!");
	}
}
