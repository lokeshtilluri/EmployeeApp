package com.lokesh.EmployeeApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lokesh.EmployeeApp.payload.EmployeeDTO;
import com.lokesh.EmployeeApp.payload.EmployeeResponse;
import com.lokesh.EmployeeApp.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {
	EmployeeService service;

	public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}
	@PostMapping("/addEmployee")
	private EmployeeDTO addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		return service.addEmployee(employeeDTO);
	}
	@GetMapping("/allEmployees")
	private List<EmployeeResponse> getAllEmployees(){
		return service.getEmployees();
	}
	@GetMapping("/{id}")
	private EmployeeResponse getEmployeeById(@PathVariable Long id) {
		return service.getEmployeeById(id);
	}
	
}
