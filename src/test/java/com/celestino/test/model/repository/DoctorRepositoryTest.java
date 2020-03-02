package com.celestino.test.model.repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.celestino.model.Doctor;
import com.celestino.model.repository.DoctorRepository;
import com.celestino.model.util.ClinicException;

public class DoctorRepositoryTest {

	private DoctorRepository DoctorRepository;

	@Before
	public void prepareDatabase() {
		System.out.println("Cleanning database...");
		DoctorRepository = new DoctorRepository();
		DoctorRepository.deleteAll();
	}

	@Test
	public void mustInsertDoctor() throws ClinicException, SQLException {
		Doctor doctor = new Doctor();
		doctor.setDoctorName("Mario C");
		doctor.setGender("M");
		doctor.setBornDate(new Date(0));
		doctor.setMaritalStatus("D");
		doctor.setCrmNumber(12345600);
		doctor.setSpecialty("Neurologista");
		doctor.setDocType("P");
		doctor.setDocNumber("FT659123");
		doctor.setNationality("Brasileiro");
		doctor.setEmail("marioc@digitalis.pt");
		doctor.setPhoneNumber("+351987456100");
		doctor.setAddress("Rua Teste teste 123");
		doctor.setPostalCode(9685123);
		doctor.setCountry("Portugal");

		DoctorRepository.saveDoctor(doctor);

		Assert.assertEquals(1, DoctorRepository.findAll().size());
	}

	@Test
	public void mustTestingFindAll() throws SQLException {
		List<Doctor> doctors = DoctorRepository.findAll();
		System.out.println(doctors.toString());
		Assert.assertEquals(0, doctors.size());
	}

	@Test
	public void mustFindByName() throws ClinicException {

		// Neste momento não user Algum
		String name = "Maira"; // Meu cenário diz pra procurar por isso na base de dados, mas como vamos achar
								// se eu não tenho o cenário? VEJA...
		Doctor d = DoctorRepository.findByName(name);
		// Logo o retorno da query deste método é nulo e o teste passa
		Assert.assertNull(d);
		
		// Aqui vamos inserir o user na base de dados
		Doctor doctor = new Doctor();
		doctor.setDoctorName("Maira");
		doctor.setGender("F");
		doctor.setBornDate(new Date(0));
		doctor.setMaritalStatus("S");
		doctor.setCrmNumber(91225169);
		doctor.setSpecialty("Gastro");
		doctor.setDocType("N");
		doctor.setDocNumber("298345455");
		doctor.setNationality("Brasileira");
		doctor.setEmail("mairacelestino@hotmail.com");
		doctor.setPhoneNumber("+3519874698856");
		doctor.setAddress("Rua Paris teste, 165");
		doctor.setPostalCode(1236459);
		doctor.setCountry("França");
		DoctorRepository.saveDoctor(doctor);

		// Daí verificamos novamente e tem que existir
		d = DoctorRepository.findByName(name);
		System.out.println("User encontrado buscado por:" + name + "----->>>>" + d);
		Assert.assertNotNull(d);

	}

	@Test
	public void mustUpdateDoctor() throws ClinicException {

		// Verificando que a Base de Dados está vazia
		Assert.assertEquals(0, DoctorRepository.findAll().size());

		// Inserindo dados na tabela User
		Doctor doctor = new Doctor();
		doctor.setDoctorName("Maira");
		doctor.setGender("F");
		doctor.setBornDate(new Date(0));
		doctor.setMaritalStatus("S");
		doctor.setCrmNumber(91225169);
		doctor.setSpecialty("Gastro");
		doctor.setDocType("N");
		doctor.setDocNumber("298345455");
		doctor.setNationality("Brasileira");
		doctor.setEmail("mairacelestino@hotmail.com");
		doctor.setPhoneNumber("+3519874698856");
		doctor.setAddress("Rua Paris teste, 165");
		doctor.setPostalCode(1236459);
		doctor.setCountry("França");
		DoctorRepository.saveDoctor(doctor);

		// Verificando se o registro foi inserido
		Assert.assertEquals(1, DoctorRepository.findAll().size());

		// Buscando User pelo nome
		Doctor nome = DoctorRepository.findByName("Maira");
		// Testando se o doctor existe
		Assert.assertNotNull(nome);

		// Atualizando user encontrado
		nome.setEmail("mairacelestino@gmail.com");
		// Atualizando informações na base de dados
		DoctorRepository.updateDoctor(nome);

		// Buscando user atualizado
		Doctor nomeUpdate = DoctorRepository.findByName("Maira");
		// Verificando se o user foi atualizado
		Assert.assertEquals("mairacelestino@gmail.com", nomeUpdate.getEmail());
		System.out.println("Email Update:" + nomeUpdate.getEmail());
	}

}
