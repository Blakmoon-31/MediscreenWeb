package com.openclassrooms.mediscreenWeb.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.mediscreenWeb.bean.PatientAssessmentBean;
import com.openclassrooms.mediscreenWeb.bean.TriggerWordBean;

@FeignClient(name = "Mediscreen-PatientAssessment", url = "localhost:8080")
public interface PatientAssessmentProxy {

	@GetMapping("/assess/id")
	PatientAssessmentBean calculatePatientAssessmentByPatientId(@RequestParam("patientId") int patientId);

	@GetMapping("/settings/triggerWords")
	List<TriggerWordBean> getTriggerWords();

	@PostMapping("/settings/triggerWord/add")
	void addTriggerWord(@RequestParam("triggerWord") String triggerWord);

	@DeleteMapping("/settings/triggerWord/delete")
	void deleteTriggerWord(@RequestParam("triggerWord") String triggerWord);
}
