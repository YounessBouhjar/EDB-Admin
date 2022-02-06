package com.example.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.admin.model.Admin;
import com.example.admin.repository.AdminRepository;



@Service
public class UserPrincipalDetailsService implements UserDetailsService {

	@Autowired
	AdminRepository rep;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Admin utilisateur=rep.findByEmail(email);
		UserPrincipal userPrincipal= new UserPrincipal(utilisateur);
		return userPrincipal;
	}

}
