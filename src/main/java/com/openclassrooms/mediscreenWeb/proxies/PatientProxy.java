package com.openclassrooms.mediscreenWeb.proxies;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.mediscreenWeb.bean.PatientBean;

@FeignClient(name = "Mediscreen-Patient", url = "localhost:8081")
public interface PatientProxy {

	@GetMapping("/patient/list")
	List<PatientBean> getPatients();

	@GetMapping("/patient/get/{id}")
	Optional<PatientBean> getPatientById(@PathVariable("id") int patientId);

	@GetMapping("/patient/get/{family}/{given}")
	PatientBean getPatientByFamilyAndGivenName(@PathVariable("family") String familyName,
			@PathVariable("given") String givenName);

	@PostMapping("/patient/add")
	void addPatient(@RequestBody PatientBean patientBean);

	@PutMapping("/patient/update")
	PatientBean updatePatient(@RequestBody PatientBean patientBean);

	@DeleteMapping("/patient/delete/{id}")
	void deletePatientById(@PathVariable("id") int patientBeanId);

}
