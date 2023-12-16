package com.lokesh.EmployeeApp.service.impl;

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
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		Employee employee = mapToEntity(employeeDTO);
		Employee empResponse = repository.save(employee);
		return mapToDTO(empResponse);
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

	private EmployeeResponse mapEntityToResponse(Employee employee) {
		EmployeeResponse response = modelMapper.map(employee, EmployeeResponse.class);
		long salary = employee.getSalary();
		long yearlySalary = salary * 12;
		if (employee.getDOJ().after(new Date(2023, 04, 01))) {
			int days = employee.getDOJ().getDay();
			int months = employee.getDOJ().getMonth();
			long salaryDeduct = days * salary / 30 + (months - 4) * salary;
			yearlySalary -= salaryDeduct;
		}
		response.setTaxAmount(taxCalculator(yearlySalary));
		long cessAmount = 0;

		if (yearlySalary > 2500000) {
			cessAmount = (yearlySalary - 25000000) / 50;
		}
		response.setCessAmount(cessAmount);
		return response;
	}

	private long taxCalculator(long salary) {
		long tax = 0;
		long taxTier1 = 0;
		long taxTier2 = 0;
		long taxTier3 = 0;
		if (salary > 250000) {
			taxTier1 = (salary - 250000) / 20;
			tax = taxTier1;
		}
		if (salary > 500000) {
			taxTier2 = taxTier1 + (salary - 500000) / 10;
			tax = taxTier2;
		}
		if (salary > 1000000) {
			taxTier3 = taxTier2 + (salary - 1000000) / 5;
			tax = taxTier3;
		}
		return tax;
	}

	private EmployeeDTO mapToDTO(Employee employee) {
		return modelMapper.map(employee, EmployeeDTO.class);
	}

	/*
	 * private EmployeeResponse mapDtoToResponse(EmployeeDTO employeeDTO) {
	 * EmployeeResponse response = modelMapper.map(employeeDTO,
	 * EmployeeResponse.class); return response; }
	 */

}
