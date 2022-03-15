package com.openclassrooms.mediscreenWeb.controller;

import java.util.List;

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
import com.openclassrooms.mediscreenWeb.service.PatientWebService;

@Controller
public class PatientWebController {

	@Autowired
	private PatientWebService patientWebService;

	@RequestMapping({ "/", "/home" })
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/patient/list")
	public String getPatients(Model model) {
		List<PatientBean> patients = patientWebService.getPatients();
		model.addAttribute("patientBeans", patients);

		return "patient/list";
	}

	@GetMapping("/patient/get/{patientId}")
	public PatientBean getPatientById(@PathVariable("patientId") int patientId) {
		return patientWebService.getPatientById(patientId);
	}

	@GetMapping("/patient/get/{familyName}/{givenName}")
	public PatientBean getPatientByFamilyAndGivenName(@PathVariable("familyName") String familyName,
			@PathVariable("givenName") String givenName) {
		return patientWebService.getPatientByFamilyAndGivenName(familyName, givenName);
	}

	@GetMapping("/patient/add")
	public String showAddPatientForm(PatientBean patientBean) {

		return "patient/add";
	}

	@PostMapping("/patient/add")
	public String validatePatient(@Valid PatientBean patientBean, BindingResult result, Model model) {

		if (!result.hasErrors()) {
			patientWebService.addPatient(patientBean);
			model.addAttribute("patientBeans", patientWebService.getPatients());
			return "redirect:/patient/list";
		}
		return "patient/add";

	}

	@GetMapping("/patient/update/{patientId}")
	public String showUpdatePatientForm(@PathVariable("patientId") int patientBeanId, Model model) {
		model.addAttribute("patientBean", patientWebService.getPatientById(patientBeanId));

		return "patient/update";
	}

	@PutMapping("/patient/update/{id}")
	public String updatePatient(@PathVariable("id") int patientBeanId, @Valid PatientBean patientBean,
			BindingResult result, Model model) {

		if (!result.hasErrors()) {

			patientBean.setPatientId(patientBeanId);
			patientWebService.updatePatient(patientBean);
			model.addAttribute("patientBeans", patientWebService.getPatients());
			return "redirect:/patient/list";
		}

		return "patient/update";
	}

	@GetMapping("/patient/delete/{patientId}")
	public String deletePatient(@PathVariable("patientId") int patientBeanId) {
		patientWebService.deletePatientById(patientBeanId);

		return "redirect:/patient/list";
	}
}
