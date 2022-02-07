package com.example.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.admin.exceptions.DuplicatedException;
import com.example.admin.exceptions.NotFoundException;
import com.example.admin.model.Admin;
import com.example.admin.repository.AdminRepository;



@Service
public class AdminService {

	@Autowired
	AdminRepository rep;
		
		
	
	public Admin getByEmail(String email)
	{
		Admin admin = rep.findByEmail(email);		
		return admin;
	
	}
	
	
	public List<Admin> getAdmins()
	{
		
		List<Admin> admins= new ArrayList<Admin>();	
		
			admins=rep.findAll();
		
		if(admins.isEmpty())  throw new NotFoundException("Aucun administrateur trouvé");
		return admins;
	}
	
	
	public void addAdmin(Admin admin) throws DuplicatedException
	{
		if(rep.findByEmail(admin.getEmail()) != null) throw new DuplicatedException(" Admin existe deja");

		admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));		
		admin.setRole("Admin");

		rep.save(admin);
		
	}
	
    
    public Admin getAdminById(Long id){
        return  rep.findAdminById(id);
    }
    
    
    //delete agent
    public Long deleteAdmin(Long id) throws NotFoundException {
        Admin adminFromDB = rep.findAdminById(id);
        if (adminFromDB == null)
            throw new NotFoundException("aucun Admin");
        rep.delete(adminFromDB);
        return id;
    }


    

    
    public Admin updateAdmin(Admin admin) throws NotFoundException {
    	Admin adminFromDB = rep.findById(admin.getId()).orElse(null);
        if (adminFromDB == null)
            throw new NotFoundException(admin.getId());
        admin.setId(adminFromDB.getId());
        return rep.save(admin);
    }


}

