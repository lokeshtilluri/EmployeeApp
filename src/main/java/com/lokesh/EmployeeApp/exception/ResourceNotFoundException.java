package com.lokesh.EmployeeApp.exception;

public class ResourceNotFoundException extends RuntimeException{
	private String resourceName;
	private String fieldName;
	private Long id;
	public ResourceNotFoundException(String resourceName, String fieldName, Long id) {
		super(String.format("%s not found with %s : %s",resourceName,fieldName,id));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.id = id;
	}
	
}
