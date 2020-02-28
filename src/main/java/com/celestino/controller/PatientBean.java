package com.celestino.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.celestino.model.Nationality;
import com.celestino.model.Patient;
import com.celestino.model.repository.PatientRepository;
import com.celestino.model.util.ClinicException;

@Named
@ViewScoped
public class PatientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Patient patient;
	private List<Nationality> nationalities;
	
	public List<Nationality> getNationalities() {
		nationalities = new ArrayList<Nationality>();
		Nationality n1 = new Nationality();
		n1.setDescription("Alemão");
		n1.setId(1);
		nationalities.add(n1);
		Nationality n2 = new Nationality();
		n2.setId(2);
		n2.setDescription("Português");
		nationalities.add(n2);
		Nationality n3 = new Nationality();
		n3.setDescription("Brasileiro");
		n3.setId(3);
		nationalities.add(n3);
		return nationalities;
	}

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
	
	
}
