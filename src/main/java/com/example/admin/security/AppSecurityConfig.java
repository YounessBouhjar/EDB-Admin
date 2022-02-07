package com.example.admin.security;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.admin.exceptions.DuplicatedException;
import com.example.admin.exceptions.NotFoundException;
import com.example.admin.model.Admin;
import com.example.admin.service.AdminService;
import com.google.common.collect.ImmutableList;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AdminService adminService;
	
	
	UserPrincipalDetailsService service;
	
	@Autowired
	public AppSecurityConfig(UserPrincipalDetailsService service) {

		this.service = service;
	}
	
	@PostConstruct
	public void init() throws DuplicatedException {
		
        
		List<Admin>  currentAdminList= new ArrayList<Admin>();
		try {
		currentAdminList = adminService.getAdmins();
		} catch (NotFoundException e) {
		
	        
			Admin    admin    = new Admin();
	        admin.setPassword("admin");
	        admin.setEmail("younessbouhjar55@gmail.com");
	        admin.setRole("Admin");
	        adminService.addAdmin(admin);
	     
	        
		}

	    
	}



	@Bean
	public DaoAuthenticationProvider autProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}
	
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(ImmutableList.of("*"));
		configuration.setAllowedMethods(ImmutableList.of("HEAD",
				"GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setAllowedHeaders(ImmutableList.of("accept",
				"accept-encoding",
				"authorization",
				"content-type",
				"Access-Control-Allow-Origin",
				"dnt",
				"origin",
				"user-agent",
				"x-csrftoken",
				"x-requested-with","Access-Control-Allow-Headers", "Accept", "X-Requested-With", "remember-me"));
		configuration.setAllowCredentials(true);

		configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/", true);

		http
			.cors()
			.and()
			.authorizeRequests()
			//ADMIN
			.antMatchers(HttpMethod.GET,"/admin/all").permitAll()		//afficher les admins
			.antMatchers(HttpMethod.GET,"/admin/username/{username}").permitAll()			//admin par username
			.antMatchers(HttpMethod.POST,"/admin/add").permitAll()			//creer les admins
			.antMatchers(HttpMethod.GET,"/admin/agent/all").permitAll()			//creer les admins
			.antMatchers("/admin").permitAll() 		//agent par username

			.antMatchers(HttpMethod.GET,"/admin/adminid/{id}").permitAll()			//creer les admins
			.antMatchers(HttpMethod.PUT,"/admin/updateadmin/{id}").permitAll()			//creer les admins
			.antMatchers(HttpMethod.DELETE,"/admin/delete/{id}").permitAll()			//creer les admins
			.antMatchers(HttpMethod.PUT,"/admin/update").permitAll()			//creer les admins


			.and()
			.httpBasic()
			.and()
			.csrf().disable()
			;
			
		
		
		super.configure(http);
	}
	
	
	

}
