package com.openclassrooms.mediscreenWeb.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreenWeb.bean.PatientHistoryBean;
import com.openclassrooms.mediscreenWeb.proxies.PatientHistoryProxy;

@Service
public class PatientHistoryWebService {

	@Autowired
	private PatientHistoryProxy patientHistoryProxy;

	public List<PatientHistoryBean> getPatientHistoriesByPatientId(int patientId) {

		List<PatientHistoryBean> patientHistoryBeans = patientHistoryProxy.getPatientHistoriesByPatientId(patientId);
		return patientHistoryBeans;
	}

	public void addPatientHistory(@Valid PatientHistoryBean patientHistoryBean) {
		patientHistoryProxy.addPatientHistory(patientHistoryBean);

	}

	public void deletePatientHistoryByPatientHistoryId(String patientHistoryId) {
		patientHistoryProxy.deletePatientHistoryByPatientHistoryId(patientHistoryId);

	}

	public PatientHistoryBean getPatientHistoriesByPatientHistoryId(String patientHistoryId) {

		return patientHistoryProxy.getPatientHistoryByHistoryId(patientHistoryId);
	}

	public void updatePatientHistory(@Valid PatientHistoryBean patientHistoryBean) {
		patientHistoryProxy.updatePatientHistory(patientHistoryBean);

	}

}
