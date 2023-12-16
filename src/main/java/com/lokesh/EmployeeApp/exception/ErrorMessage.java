package com.lokesh.EmployeeApp.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	private String error;
	private String errorMessage;
	private Date timestamp;
	
}
