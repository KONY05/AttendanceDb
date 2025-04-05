package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Parent;
import com.example.AttendanceDB.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentService {

	private final ParentRepository ParentRepository;

	// Create with safeguard: check if a parent with same phone or email already
	// exists
	public Parent saveParent(Parent parent) {
		Optional<Parent> existing = ParentRepository.findByPhoneNumberOrEmail(
				parent.getPhoneNumber(), parent.getEmail());

		if (existing.isPresent()) {
			throw new RuntimeException("A parent with this phone number or email already exists.");
		}

		return ParentRepository.save(parent);
	}

	// Get all
	public List<Parent> getAllParents() {
		return ParentRepository.findAll();
	}

	// Get by ID
	public Optional<Parent> getParentById(Integer id) {
		return ParentRepository.findById(id);
	}

	// Update
	public Parent updateParent(Integer id, Parent updatedParent) {
		return ParentRepository.findById(id).map(parent -> {
			parent.setFirstName(updatedParent.getFirstName());
			parent.setLastName(updatedParent.getLastName());
			parent.setPhoneNumber(updatedParent.getPhoneNumber());
			parent.setEmail(updatedParent.getEmail());
			parent.setAddress(updatedParent.getAddress());
			return ParentRepository.save(parent);
		}).orElseThrow(() -> new RuntimeException("Parent not found with ID: " + id));
	}

	// Delete
	public void deleteParent(Integer id) {
		ParentRepository.deleteById(id);
	}

	// Find by phone number
	public Optional<Parent> findByPhoneNumber(String phone) {
		return ParentRepository.findByPhoneNumber(phone);
	}

	// Find by email
	public Optional<Parent> findByEmail(String email) {
		return ParentRepository.findByEmail(email);
	}
}
