package com.openclassrooms.mediscreenWeb.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openclassrooms.mediscreenWeb.bean.PatientBean;
import com.openclassrooms.mediscreenWeb.proxies.PatientProxy;

@Controller
public class PatientWebController {

	@Autowired
	private PatientProxy patientProxy;

	@RequestMapping({ "/", "/home" })
	public String home(Model model) {
		return "/home";
	}

	@GetMapping("/patient/list")
	public String getPatients(Model model) {
		List<PatientBean> patients = patientProxy.getPatients();
		model.addAttribute("patientBeans", patients);

		return "patient/list";
	}

	@GetMapping("/patient/get/{id}")
	public Optional<PatientBean> getPatientById(@PathVariable("id") int patientId) {
		return patientProxy.getPatientById(patientId);
	}

	@GetMapping("/patient/get/{family}/{given}")
	public PatientBean getPatientByFamilyAndGivenName(@PathVariable("family") String familyName,
			@PathVariable("given") String givenName) {
		return patientProxy.getPatientByFamilyAndGivenName(familyName, givenName);
	}

	@GetMapping("/patient/add")
	public String showAddPatientForm(PatientBean patientBean) {

		return "patient/add";
	}

	@PostMapping("/patient/add")
	public String validatePatient(@Valid PatientBean patientBean, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			patientProxy.addPatient(patientBean);
			model.addAttribute("patientBeans", patientProxy.getPatients());
			return "redirect:/patient/list";
		}
		return "patient/add";

	}

	@GetMapping("/patient/update/{id}")
	public String showUpdatePatientForm(@PathVariable("id") int patientBeanId, Model model) {
		model.addAttribute("patientBean", patientProxy.getPatientById(patientBeanId).get());

		return "patient/update";
	}

	@PutMapping("/patient/update/{id}")
	public String updatePatient(@PathVariable("id") int patientBeanId, @Valid PatientBean patientBean,
			BindingResult result, Model model) {

		if (!result.hasErrors()) {

			patientBean.setPatientId(patientBeanId);
			patientProxy.updatePatient(patientBean);
			model.addAttribute("patientBeans", patientProxy.getPatients());
			return "redirect:/patient/list";
		}

		return "patient/update";
	}

	@GetMapping("/patient/delete/{id}")
	public String deletePatient(@PathVariable("id") int patientBeanId) {
		patientProxy.deletePatientById(patientBeanId);

		return "redirect:/patient/list";
	}
}
