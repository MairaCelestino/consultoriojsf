package com.celestino.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import com.celestino.model.Nationality;
import com.celestino.model.Patient;
import com.celestino.model.repository.NationalityRepository;
import com.celestino.model.repository.PatientRepository;
import com.celestino.model.util.ClinicException;
import com.sun.xml.internal.ws.client.RequestContext;

@Named
@ViewScoped
public class PatientBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Patient patient;

	private String filter;

	private List<Patient> patients;

	private List<Nationality> nationalities;

	@Inject
	private PatientRepository patientRepository;

	@Inject
	private NationalityRepository nationalityRepository;

	public void iniciar() {
		this.patient = new Patient();
	}

	public String save() throws ParseException {
		try {
			if (patient.getId() == null) {
				patientRepository.savePatient(patient);
				PrimeFaces current = PrimeFaces.current();
				current.executeScript("PF('dlg1').hide()");
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Paciente Cadastrado com sucesso!!"));
			} else {
				patientRepository.updatePatiente(patient);
				PrimeFaces current = PrimeFaces.current();
				current.executeScript("PF('dlg1').hide()");
				FacesMessage fmsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Paciente Atualizado com sucesso!!",
						null);
				FacesContext.getCurrentInstance().addMessage(null, fmsg);
				// FacesContext.getCurrentInstance().addMessage(null,
				// new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Paciente Atualizado
				// com sucesso!!"));
			}

			search();
			return "";
		} catch (ClinicException e) {
			e.printStackTrace();
		}
		limpar();
		return "";
	}

	public void limparFiltro() {
		this.filter = "";
	}

	public void search() {
		patients = new PatientRepository().findByName(filter);
	}

	public void delete(Long patient) {
		patientRepository.deleteById(patient);
		limpar();
		patients = null;
	}

	public void updatePatient(Patient patient) {
		this.patient = patient;
	}

	public void limpar() {
		this.patient = new Patient();
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<Patient> getPatients() {
		if (patients == null) {
			patients = patientRepository.findAll();
		}
		return patients;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Nationality> getNationalities() {
		nationalities = nationalityRepository.findAll();
		return nationalities;
	}

	public void onRowEdit(RowEditEvent event) {
		this.patient = (Patient) event.getObject();
		updatePatient(patient);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "User " + patient.getPatientName() + " Upadeted!", ""));
	}

	public void onRowCancel(RowEditEvent event) {
	}

}
