package com.lokesh.EmployeeApp.service;

import java.util.List;

import com.lokesh.EmployeeApp.payload.EmployeeDTO;
import com.lokesh.EmployeeApp.payload.EmployeeResponse;

public interface EmployeeService {
	String addEmployee(EmployeeDTO employeeDTO);
	List<EmployeeResponse> getEmployees();
	EmployeeResponse getEmployeeById(Long id);
}
