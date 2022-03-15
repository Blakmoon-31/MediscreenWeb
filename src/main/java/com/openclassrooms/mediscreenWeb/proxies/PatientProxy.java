package com.openclassrooms.mediscreenWeb.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.openclassrooms.mediscreenWeb.bean.PatientBean;

/**
 * Proxy used to connect to the Patient service. URL - The url use the name of
 * the Docker container (patient) to communicate between containers. Use
 * localhost if application is not run from a container. Ports are the same.
 * 
 * @author emmanuel
 *
 */
@FeignClient(name = "Mediscreen-Patient", url = "${patient.proxy.host}")
public interface PatientProxy {

	@GetMapping("/patient/list")
	List<PatientBean> getPatients();

	@GetMapping("/patient/get/id")
	PatientBean getPatientById(@RequestParam("patientId") int patientId);

	@GetMapping("/patient/get/name")
	PatientBean getPatientByFamilyAndGivenName(@RequestParam("familyName") String familyName,
			@RequestParam("givenName") String givenName);

	@PostMapping("/patient/add")
	void addPatient(@RequestBody PatientBean patientBean);

	@PutMapping("/patient/update")
	PatientBean updatePatient(@RequestBody PatientBean patientBean);

	@DeleteMapping("/patient/delete")
	void deletePatientById(@RequestParam("patientId") int patientBeanId);

}
