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

import com.openclassrooms.mediscreenWeb.bean.PatientAssessmentBean;
import com.openclassrooms.mediscreenWeb.bean.PatientBean;
import com.openclassrooms.mediscreenWeb.bean.PatientHistoryBean;
import com.openclassrooms.mediscreenWeb.service.PatientAssessmentWebService;
import com.openclassrooms.mediscreenWeb.service.PatientHistoryWebService;
import com.openclassrooms.mediscreenWeb.service.PatientWebService;

@Controller
public class PatientHistoryWebController {

	@Autowired
	public PatientHistoryWebService patientHistoryWebService;

	@Autowired
	private PatientAssessmentWebService patientAssessmentWebService;

	@Autowired
	public PatientWebService patientWebService;

	@GetMapping("/history/patient/{patientId}")
	public String showPatientHistoryForm(@PathVariable("patientId") int patientId, Model model) {

		PatientBean patientBean = patientWebService.getPatientById(patientId);
		model.addAttribute("patient", patientBean);

		int age = patientWebService.calculateAge(patientBean.getBirthdate());
		model.addAttribute("age", age);

		List<PatientHistoryBean> patientHistoryBeans = patientHistoryWebService
				.getPatientHistoriesByPatientId(patientId);
		model.addAttribute("patientHistoryBeans", patientHistoryBeans);

		PatientAssessmentBean patientAssessmentBean = patientAssessmentWebService
				.calculatePatientAssessmentByPatientId(patientId);
		model.addAttribute("assessment", patientAssessmentBean);

		return "history/patient";

	}

	@GetMapping("/history/updatePatient/{patientId}")
	public String showUpdatePatientFormFromHistory(@PathVariable("patientId") int patientBeanId, Model model) {
		model.addAttribute("patientBean", patientWebService.getPatientById(patientBeanId));

		return "history/updatePatient";
	}

	@PutMapping("/history/updatePatient/{patientId}")
	public String updatePatientFromHistory(@PathVariable("patientId") int patientBeanId, @Valid PatientBean patientBean,
			BindingResult result, Model model) {

		if (!result.hasErrors()) {

			patientBean.setPatientId(patientBeanId);
			patientWebService.updatePatient(patientBean);
			model.addAttribute("patientBean", patientWebService.getPatientById(patientBeanId));
			return "redirect:/history/patient/" + patientBeanId;
		}

		return "history/updatePatient";
	}

	@GetMapping("/history/addHistory/{patientBeanId}")
	public String showAddHistoryForm(@PathVariable("patientBeanId") int patientBeanId,
			PatientHistoryBean patientHistoryBean, Model model) {

		PatientBean patientBean = patientWebService.getPatientById(patientBeanId);
		model.addAttribute("patient", patientBean);

		int patientAge = patientWebService.calculateAge(patientBean.getBirthdate());
		model.addAttribute("patientAge", patientAge);

		return "history/addHistory";
	}

	@PostMapping("/history/addHistory/{patientId}")
	public String addPatientHistory(@PathVariable("patientId") int patientBeanId,
			@Valid PatientHistoryBean patientHistoryBean, BindingResult result, Model model) {

		if (!result.hasErrors()) {

			patientHistoryBean.setPatientId(patientBeanId);
			patientHistoryWebService.addPatientHistory(patientHistoryBean);
			return "redirect:/history/patient/" + patientBeanId;
		}

		return "history/addHistory";
	}

	@GetMapping("/history/updateHistory/{patientHistoryId}")
	public String showUpdatePatientHistoryForm(@PathVariable("patientHistoryId") String patientHistoryId, Model model) {

		PatientHistoryBean patientHistoryBean = patientHistoryWebService
				.getPatientHistoriesByPatientHistoryId(patientHistoryId);
		PatientBean patientBean = patientWebService.getPatientById(patientHistoryBean.getPatientId());
		model.addAttribute("patientHistoryBean", patientHistoryBean);
		model.addAttribute("patient", patientBean);
		model.addAttribute("age", patientWebService.calculateAge(patientBean.getBirthdate()));

		return "history/updateHistory";
	}

	@PutMapping("/history/updateHistory/{patientHistoryId}")
	public String updatePatientHistory(@PathVariable("patientHistoryId") String patientHistoryId,
			@Valid PatientHistoryBean patientHistoryBean, BindingResult result, Model model) {

		if (!result.hasErrors()) {

			patientHistoryBean.setHistoryId(patientHistoryId);
			patientHistoryWebService.updatePatientHistory(patientHistoryBean);
			model.addAttribute("patientBeans", patientWebService.getPatients());
			return "redirect:/history/patient/" + patientHistoryBean.getPatientId();
		}

		return "history/updateHistory";
	}

	@GetMapping("/history/delete/{patientHistoryId}/{patientId}")
	public String deletePatientHistory(@PathVariable("patientHistoryId") String patientHistoryId,
			@PathVariable("patientId") int patientId) {

		patientHistoryWebService.deletePatientHistoryByPatientHistoryId(patientHistoryId);

		return "redirect:/history/patient/" + patientId;
	}
}
