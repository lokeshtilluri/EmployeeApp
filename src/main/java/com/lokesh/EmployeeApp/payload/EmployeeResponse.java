package com.lokesh.EmployeeApp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
	private Long employeeId;
	private String firstName;
	private String lastName;
	private Long salary;
	private long taxAmount;
	private long cessAmount;
}
