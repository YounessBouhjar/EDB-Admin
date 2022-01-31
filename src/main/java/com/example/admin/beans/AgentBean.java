package com.example.admin.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentBean {
    private Long id;
    private String nom;
    private String prenom;
    private String password;
    private String email;
	private String role;

}
