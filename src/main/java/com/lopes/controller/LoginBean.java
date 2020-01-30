package com.lopes.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String password;
	private String user;

//    public String autenticar()
//    {
//        if (this.getUser().trim().equalsIgnoreCase("ADMIN") && this.getPassword().equalsIgnoreCase("ADMIN"))
//            return "home?faces-redirect=true";
//        else
//        {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "Credenciais inválidas", "Please enter correct username and Password"));
//            return "";
//        }
//    }

	public String autenticar() {
		if (this.getUser().trim().equalsIgnoreCase("ADMIN")) {
			if (this.getPassword().equalsIgnoreCase("ADMIN")) {
				return "home?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Invalid pasword!", "Please enter correct Password"));
				return "";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Username!", "Please enter correct Username"));
			return "";
		}
	}

	public String getPassword() {
		return password;
	}

	public String getUser() {
		return user;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout com sucesso!", ""));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "/login?faces-redirect=true";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
