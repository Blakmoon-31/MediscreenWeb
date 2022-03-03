package com.openclassrooms.mediscreenWeb.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreenWeb.bean.PatientBean;
import com.openclassrooms.mediscreenWeb.proxies.PatientProxy;

@Service
public class PatientWebService {

	@Autowired
	private PatientProxy patientProxy;

	public List<PatientBean> getPatients() {
		List<PatientBean> patients = patientProxy.getPatients();

		return patients;
	}

	public PatientBean getPatientById(int patientBeanId) {
		return patientProxy.getPatientById(patientBeanId);
	}

	public PatientBean getPatientByFamilyAndGivenName(String familyName, String givenName) {
		return patientProxy.getPatientByFamilyAndGivenName(familyName, givenName);
	}

	public void addPatient(PatientBean patientBean) {
		patientProxy.addPatient(patientBean);
	}

	public void updatePatient(PatientBean patientBean) {
		patientProxy.addPatient(patientBean);
	}

	public void deletePatientById(int patientBeanId) {
		patientProxy.deletePatientById(patientBeanId);
	}

	public int calculateAge(LocalDate birthdate) {
		LocalDate today = LocalDate.now();
		int age = Period.between(birthdate, today).getYears();

		return age;

	}
}
