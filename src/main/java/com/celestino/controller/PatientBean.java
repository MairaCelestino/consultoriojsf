package com.celestino.controller;

import java.io.Serializable;
import java.text.ParseException;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.celestino.model.Patient;
import com.celestino.model.repository.PatientRepository;
import com.celestino.model.util.ClinicException;

@Named
@ViewScoped
public class PatientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Patient patient;
	
	private String dateTemp;

	@Inject
	private PatientRepository patientRepository;

	public void iniciar() {
		this.patient = new Patient();
	}

	public String inserir() throws ParseException {
		try {
			patientRepository.savePatient(patient);
		} catch (ClinicException e) {
			e.printStackTrace();
		}
		limpar();
		return "";
	}

	public void limpar() {
		this.patient = new Patient();
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public String getDateTemp() {
		return dateTemp;
	}

	public void setDateTemp(String dateTemp) {
		this.dateTemp = dateTemp;
	}

}
