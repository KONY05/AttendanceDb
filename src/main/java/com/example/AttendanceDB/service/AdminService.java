package com.example.AttendanceDB.service;

import com.example.AttendanceDB.entity.Admin;
import com.example.AttendanceDB.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepository adminRepository;

	// Save new admin, prevent duplicates by email
	public Admin saveAdmin(Admin admin) {
		if (adminRepository.existsByEmail(admin.getEmail())) {
			throw new RuntimeException("Admin already exists with email: " + admin.getEmail());
		}
		return adminRepository.save(admin);
	}

	// Get all admins
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	// Get a specific admin by ID
	public Optional<Admin> getAdminById(Integer id) {
		return adminRepository.findById(id);
	}

	// Update admin
	public Admin updateAdmin(Integer id, Admin updatedAdmin) {
		return adminRepository.findById(id).map(admin -> {
			admin.setName(updatedAdmin.getName());
			admin.setRole(updatedAdmin.getRole());
			admin.setEmail(updatedAdmin.getEmail());
			admin.setPhoneNumber(updatedAdmin.getPhoneNumber());
			return adminRepository.save(admin);
		}).orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
	}

	// Delete admin
	public void deleteAdmin(Integer id) {
		adminRepository.deleteById(id);
	}
}
