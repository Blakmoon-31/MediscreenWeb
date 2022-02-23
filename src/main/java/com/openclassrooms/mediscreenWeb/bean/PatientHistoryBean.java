package com.openclassrooms.mediscreenWeb.bean;

import lombok.Data;

@Data
public class PatientHistoryBean {

	private int historyID;

	private int patientId;

	private String practitionerNote;

}
