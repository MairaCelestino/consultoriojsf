package com.celestino.test.model.repository;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.celestino.model.User;
import com.celestino.model.repository.UserRepository;
import com.celestino.model.util.ClinicException;

public class UserRepositoryTest {

	@Before
	public void prepareDatabase() {
		System.out.println("Cleanning database...");
		UserRepository.deleteAll();
	}

	@Test
	public void mustTestingFindAll() throws SQLException {
		List<User> users = UserRepository.findAll();
		System.out.println(users.toString());
		Assert.assertEquals(0, users.size());
	}

	@Test
	public void mustInsertUser() throws ClinicException, SQLException {
		User user = new User();
		user.setName("teste");
		user.setLogin("teste");
		user.setPassword("teste");

		UserRepository.saveUser(user);

		Assert.assertEquals(1, UserRepository.findAll().size());

	}

	@Test
	public void mustFindByName() throws ClinicException {
		
		//Neste momento não user Algum
		String name = "XPT1"; // Meu cenário diz pra procurar por isso na base de dados, mas como vamos achar se eu não tenho o cenário? VEJA...
		User u = UserRepository.findByName(name);
		//Logo o retorno da query deste método é nulo e o teste passa
		Assert.assertNull(u);
		
		// Aqui vamos inserir o user na base de dados
		User user = new User();
		user.setName("XPT1");
		user.setLogin("XPTO");
		user.setPassword("XPTO");
		UserRepository.saveUser(user);
		
		//Daí verificamos novamente e tem que existir
		u = UserRepository.findByName(name);
		System.out.println("User encontrado buscado por:" + name +"----->>>>" + u);
		Assert.assertNotNull(u);

	}
}
