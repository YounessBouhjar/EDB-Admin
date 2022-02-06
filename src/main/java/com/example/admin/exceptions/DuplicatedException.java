package com.example.admin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
@ResponseStatus(HttpStatus.CONFLICT)

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class DuplicatedException extends Exception {
    public DuplicatedException(String string) {
    	
		super(string);
         }


}
