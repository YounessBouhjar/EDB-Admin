package com.example.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String password;
	String email;
	String role;
	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
}
