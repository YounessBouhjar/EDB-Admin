package com.example.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.admin.exceptions.NotFoundException;
import com.example.admin.model.Admin;
import com.example.admin.repository.AdminRepository;


@Service
public class AdminService {

	@Autowired
	AdminRepository rep;
		
		
	
	public Admin getByEmail(String email)
	{
		Admin admin = rep.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Aucun administrateur avec le username "+email+" trouvé"));

		
		return admin;
	
	}
	
	
	public List<Admin> getAdmins()
	{
		
		List<Admin> admins= new ArrayList<Admin>();	
		
			admins=rep.findAll();
		
		if(admins.isEmpty())  throw new NotFoundException("Aucun administrateur trouvé");
		return admins;
	}
	
	
	public void addAdmin(Admin admin)
	{
		
		admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));				
		rep.save(admin);
		
	}


}

