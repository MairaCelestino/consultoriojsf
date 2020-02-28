package com.celestino.test.model.repository;

import com.celestino.model.Patient;
import com.celestino.model.repository.PatientRepository;
import com.celestino.model.util.ClinicException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class PatientRepositoryTest {

	private PatientRepository PatientRepository;

	@Before
	public void prepareDatabase() {
		System.out.println("Cleanning database...");
		PatientRepository = new PatientRepository();
		PatientRepository.deleteAll();
	}

	@Test
	public void mustInsertPatient() throws ClinicException, SQLException {
		Patient patient = new Patient();
		patient.setPatientName("Daniela F");
		patient.setGender("F");
		patient.setBornDate(new Date(0));
		patient.setMarital_status("C");
		patient.setDocType("P");
		patient.setDocNumber("FT659800");
		patient.setNationality("Brasileiro");
		patient.setEmail("fernandac@digitalis.pt");
		patient.setPhoneNumber("+351987456102");
		patient.setAddress("Rua Teste teste teste");
		patient.setPostal_code(9685123);
		patient.setCountry("Brasil");

		PatientRepository.savePatient(patient);

		Assert.assertEquals(1, PatientRepository.findAll().size());
	}

	@Test
	public void mustTestingFindAll() throws SQLException {
		List<Patient> patients = PatientRepository.findAll();
		System.out.println(patients.toString());
		Assert.assertEquals(0, patients.size());
	}

}
