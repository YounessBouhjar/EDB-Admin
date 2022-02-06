package com.example.admin.beans;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class NotificationBean {
	@NotBlank
    private  String phoneNumber; // destination
    @NotBlank
    private  String message;
}
