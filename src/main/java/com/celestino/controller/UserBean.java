package com.celestino.controller;

import java.io.Serializable;

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
		return "";
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

}
