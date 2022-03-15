package com.openclassrooms.mediscreenWeb.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.mediscreenWeb.bean.PatientHistoryBean;

/**
 * Proxy used to connect to the PatientHistory service. URL - The url use the
 * name of the Docker container (patienthistory) to communicate between
 * containers. Use localhost if application is not run from a container. Ports
 * are the same.
 * 
 * @author emmanuel
 *
 */
@FeignClient(name = "Mediscreen-PatientHistory", url = "${patientHistory.proxy.host}")
public interface PatientHistoryProxy {

	@GetMapping("/patient/histories/get")
	List<PatientHistoryBean> getPatientHistoriesByPatientId(@RequestParam("patientId") int patientId);

	@GetMapping("/patient/history/get")
	PatientHistoryBean getPatientHistoryByHistoryId(@RequestParam("historyId") String historyId);

	@PostMapping("/patient/history/add")
	void addPatientHistory(@RequestBody PatientHistoryBean patientHistoryBean);

	@PutMapping("/patient/history/update")
	PatientHistoryBean updatePatientHistory(@RequestBody PatientHistoryBean patientHistoryBean);

	@DeleteMapping("/patient/history/delete")
	void deletePatientHistoryByPatientHistoryId(@RequestParam("historyId") String historyId);

	@DeleteMapping("/patient/histories/delete")
	void deleteAllPatientHistoriesByPatientId(@RequestParam("patientId") int patientId);

}
