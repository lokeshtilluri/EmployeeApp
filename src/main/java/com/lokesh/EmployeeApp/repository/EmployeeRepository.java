package com.lokesh.EmployeeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lokesh.EmployeeApp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
