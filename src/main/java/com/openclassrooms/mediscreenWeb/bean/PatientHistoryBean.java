package com.openclassrooms.mediscreenWeb.bean;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PatientHistoryBean {

	private String historyId;

	private int patientId;

	private LocalDate historyDate;

	private String practitionerNote;

}
