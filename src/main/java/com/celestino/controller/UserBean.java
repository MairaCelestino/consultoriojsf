package com.celestino.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.celestino.model.User;
import com.celestino.model.repository.UserRepository;
import com.celestino.model.util.ClinicException;

@Named
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user;
	private String filter;

	private List<User> users;

	@Inject
	private UserRepository userRepository;

	public void search() {
		users = new UserRepository().findByName(filter);
	}

	public String updateUser(User user) {
		userRepository.update(user);
		limpar();
		return null;
	}

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
	
	public void limparFiltro() {
		this.filter = "";
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

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void onRowEdit(RowEditEvent event) {
		this.user = (User) event.getObject();
		updateUser(user);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "User " + user.getName() + " Upadeted!", ""));
	}

	public void onRowCancel(RowEditEvent event) {
	}

}
