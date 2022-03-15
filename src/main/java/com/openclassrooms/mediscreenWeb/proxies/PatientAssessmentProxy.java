package com.openclassrooms.mediscreenWeb.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.mediscreenWeb.bean.PatientAssessmentBean;
import com.openclassrooms.mediscreenWeb.bean.TriggerWordBean;

/**
 * Proxy used to connect to the PatientHistory service. URL - The url use the
 * name of the Docker container (patientassessment) to communicate between
 * containers. Use localhost if application is not run from a container. Ports
 * are the same.
 * 
 * @author emmanuel
 *
 */
@FeignClient(name = "Mediscreen-PatientAssessment", url = "${patientassessment.proxy.host}")
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
