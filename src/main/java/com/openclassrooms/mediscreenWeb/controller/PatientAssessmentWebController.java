package com.openclassrooms.mediscreenWeb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.openclassrooms.mediscreenWeb.bean.PatientAssessmentBean;
import com.openclassrooms.mediscreenWeb.bean.PatientBean;
import com.openclassrooms.mediscreenWeb.service.PatientAssessmentWebService;
import com.openclassrooms.mediscreenWeb.service.PatientWebService;

@Controller
public class PatientAssessmentWebController {

	@Autowired
	private PatientAssessmentWebService patientAssessmentWebService;

	@Autowired
	private PatientWebService patientWebService;

	@GetMapping("/assess/assess")
	public String showAssessmentForm(Model model) {

		List<PatientBean> patientBeans = patientWebService.getPatients();
		model.addAttribute("patientsList", patientBeans);

		PatientAssessmentBean patientAssessmentBean = new PatientAssessmentBean();
		model.addAttribute("patientAssessmentBean", patientAssessmentBean);

		List<PatientAssessmentBean> patientAssessmentBeans = new ArrayList<>();
		model.addAttribute("patientAssessmentBeans", patientAssessmentBeans);

		return "assess/assess";
	}

	@GetMapping("/assess/id/")
	public String calculatePatientAssessmentByPatientId(PatientAssessmentBean patientAssessmentBean, Model model) {

		List<PatientAssessmentBean> patientAssessmentBeans = new ArrayList<>();

		if (patientAssessmentBean.getPatientId() != 0) {
			patientAssessmentBeans.add(patientAssessmentWebService
					.calculatePatientAssessmentByPatientId(patientAssessmentBean.getPatientId()));
			PatientBean patientBean = patientWebService.getPatientById(patientAssessmentBean.getPatientId());
			model.addAttribute("patient", patientBean);
		} else {
			model.addAttribute("errorEvaluate", "Please, choose a patient");
		}

		model.addAttribute("patientAssessmentBeans", patientAssessmentBeans);

		List<PatientBean> patientBeans = patientWebService.getPatients();
		model.addAttribute("patientsList", patientBeans);

		model.addAttribute("patientAssessmentBean", patientAssessmentBean);

		return "assess/assess";
	}
}
