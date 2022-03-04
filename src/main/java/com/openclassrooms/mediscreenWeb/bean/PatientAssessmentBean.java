package com.openclassrooms.mediscreenWeb.bean;

import lombok.Data;

@Data
public class PatientAssessmentBean {

	private int patientId;

	private int age;

	private String assessmentResult;
}
