package com.openclassrooms.mediscreenWeb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreenWeb.bean.PatientAssessmentBean;
import com.openclassrooms.mediscreenWeb.proxies.PatientAssessmentProxy;

@Service
public class PatientAssessmentWebService {

	@Autowired
	private PatientAssessmentProxy patientAssessmentProxy;

	public PatientAssessmentBean calculatePatientAssessmentByPatientId(int patientId) {
		PatientAssessmentBean patientAssessmentBean = patientAssessmentProxy
				.calculatePatientAssessmentByPatientId(patientId);
		return patientAssessmentBean;
	}

}
