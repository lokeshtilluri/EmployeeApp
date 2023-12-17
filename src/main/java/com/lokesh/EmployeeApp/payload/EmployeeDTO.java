package com.lokesh.EmployeeApp.payload;

import java.util.List;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
	private Long employeeId;
	@NotBlank
	@Size(min=5, message="First name should be atleast 5 characters")
	private String firstName;
	@NotBlank
	@Size(min = 5,message="Last name should be atleast 5 characters")
	private String lastName;
	@Email(message = "please enter valid email")
	@Size(min=8,message="please enter valid email")
	private String email;
	private List<String> phoneNumbers;
	private java.sql.Date DOJ;
	@Digits(integer=20,fraction=0)
	private Long salary;
	private String taxAmount;
	private String cessAmount;
}
