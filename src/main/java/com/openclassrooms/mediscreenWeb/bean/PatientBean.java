package com.openclassrooms.mediscreenWeb.bean;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

	@NotNull(message = "Birthdate is mandatory")
	private LocalDate birthdate;

	private String address;

	private String phone;

}
