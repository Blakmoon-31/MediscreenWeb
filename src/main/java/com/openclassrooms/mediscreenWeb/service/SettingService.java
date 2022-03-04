package com.openclassrooms.mediscreenWeb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreenWeb.bean.TriggerWordBean;
import com.openclassrooms.mediscreenWeb.proxies.PatientAssessmentProxy;

@Service
public class SettingService {

	@Autowired
	private PatientAssessmentProxy patientAssessmentProxy;

	public List<TriggerWordBean> getTriggerWords() {
		return patientAssessmentProxy.getTriggerWords();
	}

	public void addTriggerWord(TriggerWordBean triggerWord) {
		patientAssessmentProxy.addTriggerWord(triggerWord);

	}

	public void deleteTriggerWord(String triggerWord) {
		patientAssessmentProxy.deleteTriggerWord(triggerWord);

	}

}
