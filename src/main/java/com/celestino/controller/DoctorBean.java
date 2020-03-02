package com.celestino.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.celestino.model.Doctor;
import com.celestino.model.Nationality;
import com.celestino.model.repository.DoctorRepository;
import com.celestino.model.repository.NationalityRepository;
import com.celestino.model.util.ClinicException;

@Named
@ViewScoped
public class DoctorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Doctor doctor;
	private List<Nationality> nationalities;

	@Inject
	private DoctorRepository doctorRepository;

	@Inject
	private NationalityRepository nationalityRepository;

	public void iniciar() {
		this.doctor = new Doctor();
	}

	public String inserir() throws ParseException {
		try {
			doctorRepository.saveDoctor(doctor);
		} catch (ClinicException e) {
			e.printStackTrace();
		}
		limpar();
		return "";
	}

	public void limpar() {
		this.doctor = new Doctor();
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public List<Nationality> getNationalities() {
		nationalities = nationalityRepository.findAll();
		return nationalities;
	}

}
