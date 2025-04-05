package com.example.AttendanceDB.controller;

import com.example.AttendanceDB.entity.Library;
import com.example.AttendanceDB.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@RequiredArgsConstructor
public class LibraryController {

	private final LibraryService libraryService;

	@PostMapping
	public ResponseEntity<String> saveBook(@RequestBody Library book) {
		Library saved = libraryService.saveBook(book);
		return ResponseEntity.ok("Book saved with ID: " + saved.getLibraryId());
	}

	@GetMapping
	public List<Library> getAllBooks() {
		return libraryService.getAllBooks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Library> getBookById(@PathVariable Integer id) {
		return libraryService.getBookById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateBook(@PathVariable Integer id, @RequestBody Library updatedBook) {
		Library updated = libraryService.updateBook(id, updatedBook);
		return ResponseEntity.ok("Book updated with ID: " + updated.getLibraryId());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
		libraryService.deleteBook(id);
		return ResponseEntity.ok("Book deleted successfully");
	}

	@GetMapping("/student/{studentId}")
	public List<Library> getBooksByStudentId(@PathVariable String studentId) {
		return libraryService.getBooksByStudentId(studentId);
	}
}
