package com.openclassrooms.mediscreenWeb.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.openclassrooms.mediscreenWeb.bean.PatientBean;
import com.openclassrooms.mediscreenWeb.controller.PatientWebController;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class PatientWebControllerIT {

	@Autowired
	private PatientWebController patientWebController;

	@BeforeAll
	public void setData() {

		BindingResult result = mock(BindingResult.class);
		Model model = mock(Model.class);

		PatientBean patientBeanToAdd1 = new PatientBean();
		patientBeanToAdd1.setFamilyName("TestAdd1");
		patientBeanToAdd1.setGivenName("Add1");
		patientBeanToAdd1.setSex("M");
		patientBeanToAdd1.setBirthdate(LocalDate.now());
		patientBeanToAdd1.setAddress("Adress 1");
		patientBeanToAdd1.setPhone("111-2222-333");

		patientWebController.validatePatient(patientBeanToAdd1, result, model);

		PatientBean patientBeanToAdd2 = new PatientBean();
		patientBeanToAdd2.setFamilyName("TestAdd2");
		patientBeanToAdd2.setGivenName("Add2");
		patientBeanToAdd2.setSex("M");
		patientBeanToAdd2.setBirthdate(LocalDate.now());

		patientWebController.validatePatient(patientBeanToAdd2, result, model);

	}

	@AfterAll
	private void resetData() {

		PatientBean patientBean = patientWebController.getPatientByFamilyAndGivenName("TestAdd", "Add");
		patientWebController.deletePatient(patientBean.getPatientId());

		PatientBean patientBean1 = patientWebController.getPatientByFamilyAndGivenName("TestAdd1", "Add1");
		patientWebController.deletePatient(patientBean1.getPatientId());

	}

	@Test
	public void testGetPatients() {

		Model model = mock(Model.class);

		String response = patientWebController.getPatients(model);

		assertThat(response).isEqualTo("patient/list");
	}

	@Test
	public void testGetPatientById() {

		PatientBean patientBean = patientWebController.getPatientById(2);

		assertThat(patientBean.getFamilyName()).isEqualTo("TestBorderline");
	}

	@Test
	public void testGetPatientByFamilyAndGivenName() {

		PatientBean patientBean = patientWebController.getPatientByFamilyAndGivenName("TestEarlyOnset", "Test");

		assertThat(patientBean.getPatientId()).isEqualTo(4);
	}

	@Test
	public void testShowAddPatientForm() {

		PatientBean patientBeanToAdd = new PatientBean();
		String response = patientWebController.showAddPatientForm(patientBeanToAdd);

		assertThat(response).isEqualTo("patient/add");
	}

	@Test
	public void testValidatePatient() {

		BindingResult result = mock(BindingResult.class);
		Model model = mock(Model.class);

		PatientBean patientToValidate = new PatientBean();
		patientToValidate.setFamilyName("TestAdd");
		patientToValidate.setGivenName("Add");
		patientToValidate.setSex("M");
		patientToValidate.setBirthdate(LocalDate.now());

		String response = patientWebController.validatePatient(patientToValidate, result, model);

		assertThat(response).isEqualTo("redirect:/patient/list");

	}

	@Test
	public void testShowUpdatePatientForm() {

		Model model = mock(Model.class);

		PatientBean patientBeanToUpdate = patientWebController.getPatientByFamilyAndGivenName("TestAdd1", "Add1");
		String response = patientWebController.showUpdatePatientForm(patientBeanToUpdate.getPatientId(), model);

		assertThat(response).isEqualTo("patient/update");
	}

	@Test
	public void testUpdatePatient() {

		BindingResult result = mock(BindingResult.class);
		Model model = mock(Model.class);

		PatientBean patientBeanToUpdate = patientWebController.getPatientByFamilyAndGivenName("TestAdd1", "Add1");
		patientBeanToUpdate.setSex("F");

		String response = patientWebController.validatePatient(patientBeanToUpdate, result, model);

		PatientBean patientBeanUpdated = patientWebController.getPatientById(patientBeanToUpdate.getPatientId());

		assertThat(response).isEqualTo("redirect:/patient/list");
		assertThat(patientBeanUpdated.getSex()).isEqualTo("F");
	}

	@Test
	public void testDeletePatient() {

		PatientBean patientBeanToDelete = patientWebController.getPatientByFamilyAndGivenName("TestAdd2", "Add2");
		String response = patientWebController.deletePatient(patientBeanToDelete.getPatientId());

		PatientBean patientBeanDeleted = patientWebController.getPatientById(patientBeanToDelete.getPatientId());

		assertThat(response).isEqualTo("redirect:/patient/list");
		assertThat(patientBeanDeleted).isNull();
	}
}
