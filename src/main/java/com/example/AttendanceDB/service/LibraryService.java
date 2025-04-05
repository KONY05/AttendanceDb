package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Library;
import com.example.AttendanceDB.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryService {

	private final LibraryRepository libraryRepository;

	// Save a new book record (issue)
	public Library saveBook(Library book) {
		return libraryRepository.save(book);
	}

	// Get all library records
	public List<Library> getAllBooks() {
		return libraryRepository.findAll();
	}

	// Get a specific book record by ID
	public Optional<Library> getBookById(Integer id) {
		return libraryRepository.findById(id);
	}

	// Update a book record
	public Library updateBook(Integer id, Library updatedBook) {
		return libraryRepository.findById(id).map(book -> {
			book.setTitle(updatedBook.getTitle());
			book.setAuthor(updatedBook.getAuthor());
			book.setIsbn(updatedBook.getIsbn());
			book.setStudent(updatedBook.getStudent());
			book.setIssueDate(updatedBook.getIssueDate());
			book.setReturnDate(updatedBook.getReturnDate());
			return libraryRepository.save(book);
		}).orElseThrow(() -> new RuntimeException("Book record not found with ID: " + id));
	}

	// Delete a book record
	public void deleteBook(Integer id) {
		libraryRepository.deleteById(id);
	}

	// Get all books issued to a specific student
	public List<Library> getBooksByStudentId(String studentId) {
		return libraryRepository.findByStudent_Id(studentId);
	}
}
