package com.celestino.test.model.repository;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.celestino.model.User;
import com.celestino.model.repository.UserRepository;

public class UserRepositoryTest {
	
	@Test
	public void mustTestingFindAll() throws SQLException {
		List<User> users = UserRepository.findAll();
		System.out.println(users.toString());
		Assert.assertEquals(1, users.size());
	}
	

}
