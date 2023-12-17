package com.lokesh.EmployeeApp.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String email;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="employee_phone_numbers",joinColumns = @JoinColumn(name="employee_id"))
	private List<String> phoneNumbers;
	private java.sql.Date DOJ;
	private Long salary;
}
