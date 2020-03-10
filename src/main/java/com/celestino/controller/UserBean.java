package com.celestino.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.celestino.model.User;
import com.celestino.model.repository.UserRepository;
import com.celestino.model.util.ClinicException;

@Named
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;

	private List<User> users;

	@Inject
	private UserRepository userRepository;

	public void iniciar() {
		this.user = new User();
	}

	public String inserir() {
		try {
			userRepository.save(user);

		} catch (ClinicException e) {
			e.printStackTrace();
		}
		limpar();
		users = null;
		return "";
	}

	public void delete(Long user) {
		userRepository.deleteById(user);
		limpar();
		users = null;
	}

	public void limpar() {
		this.user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		if (users == null) {
			users = userRepository.findAll();
		}
		return users;
	}
}
