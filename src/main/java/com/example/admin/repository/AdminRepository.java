package com.example.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.admin.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
	Optional<Admin> findByEmail(String email);
}
