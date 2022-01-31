package com.example.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.admin.model.Admin;


public class UserPrincipal implements UserDetails {

	
	
	private static final long serialVersionUID = 1L;
	
	Admin admin;
	
	@Autowired
	public UserPrincipal(Admin admin) {
		super();
		this.admin=admin;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		GrantedAuthority authority= new SimpleGrantedAuthority(this.admin.getRole()); 
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return this.admin.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.admin.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
