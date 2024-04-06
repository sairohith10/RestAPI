package com.tcs.springex.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDetails {
	@NotBlank(message = "Hospital name is required")
	private String name;
	
	@NotBlank(message = "Location  is required")
	private String loc;
	
	
	private double rating; 
	
	@Email(message = "Invalid email address")
	@NotBlank(message =  "Email is required")
	private String email;
	
	@Pattern(regexp = "[0-9]{10}", message =  "Invalid mobile number")
	@NotBlank(message =  "Mobile number is required")
	private String mobile;
}

//get name
// name and location
// get rating btw 6.5 to 7.5
// get by giving list of hospitals