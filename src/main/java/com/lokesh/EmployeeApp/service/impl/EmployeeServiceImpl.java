package com.lokesh.EmployeeApp.service.impl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.lokesh.EmployeeApp.entity.Employee;
import com.lokesh.EmployeeApp.exception.ResourceNotFoundException;
import com.lokesh.EmployeeApp.payload.EmployeeDTO;
import com.lokesh.EmployeeApp.payload.EmployeeResponse;
import com.lokesh.EmployeeApp.repository.EmployeeRepository;
import com.lokesh.EmployeeApp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	EmployeeRepository repository;
	ModelMapper modelMapper;

	public EmployeeServiceImpl(EmployeeRepository repository, ModelMapper modelMapper) {
		super();
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public String addEmployee(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		Employee employee = mapToEntity(employeeDTO);
		Employee empResponse = repository.save(employee);
		if(empResponse != null) return "Employee added succesfully";
		else return "Please provide valid data";
	}

	@Override
	public List<EmployeeResponse> getEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees = repository.findAll();
		List<EmployeeResponse> responseDTO = employees.stream().map((employee) -> mapEntityToResponse(employee))
				.collect(Collectors.toList());
		return responseDTO;
	}

	@Override
	public EmployeeResponse getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employees", "Employee_id", id));
		return mapEntityToResponse(employee);
	}

	private Employee mapToEntity(EmployeeDTO employeeDTO) {
		return modelMapper.map(employeeDTO, Employee.class);
	}

	private EmployeeResponse mapEntityToResponse(Employee employee){

		EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);
		long salary = employee.getSalary();
		long yearlySalary = salary * 12;
		java.sql.Date start = java.sql.Date.valueOf("2023-01-04");
		java.sql.Date doj = response.getDoj();
		LocalDate localDoj = doj.toLocalDate();
		LocalDate localStart = start.toLocalDate();
		if (doj.after(start)) {
			int days = localDoj.getDayOfMonth()-1;
			int months = localDoj.getMonthValue()-1;
			if(months<=3 && localDoj.getYear()!=localStart.getYear()) {
				months = months+9;
			}
			else {
				months = months-4;
			}
			long salaryDeduct = (days) * salary / 30 + Math.abs((months)) * salary;
			yearlySalary -= salaryDeduct;
		}
		response.setTaxAmount(taxCalculator(yearlySalary));
		long cessAmount = 0;

		if (yearlySalary > 2500000) {
			cessAmount = (yearlySalary - 2500000) / 50;
		}
		response.setCessAmount(cessAmount);
		return response;
	}

	private long taxCalculator(long salary) {
		long tax = 0;
		
		if (salary > 250000 && salary<=500000) {
			tax = (salary - 250000) / 20;
			
		}
		else if (salary > 500000 && salary<=1000000) {
			tax = (250000) / 20 + ((salary - 500000) / 10);
			
		}
		else if (salary > 1000000) {
			tax = (250000) / 20 + 500000 / 10 + ((salary - 1000000) / 5);
		}
		return tax;
	}

	/*
	 * private EmployeeDTO mapToDTO(Employee employee) { return
	 * modelMapper.map(employee, EmployeeDTO.class); }
	 */

	/*
	 * private EmployeeResponse mapDtoToResponse(EmployeeDTO employeeDTO) {
	 * EmployeeResponse response = modelMapper.map(employeeDTO,
	 * EmployeeResponse.class); return response; }
	 */

}
