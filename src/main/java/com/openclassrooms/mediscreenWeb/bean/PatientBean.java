package com.openclassrooms.mediscreenWeb.bean;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PatientBean {

	private int patientId;

	@NotBlank(message = "Family name is mandatory")
	private String familyName;

	@NotBlank(message = "Given name is mandatory")
	private String givenName;

	@NotBlank(message = "Sex is mandatory")
	private String sex;

//	@NotBlank(message = "Birthdate is mandatory")
	private LocalDateTime birthdate;

	private String address;

	private String phone;

}
