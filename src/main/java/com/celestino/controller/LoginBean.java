package com.celestino.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password;
    private String user;

    public String autenticar() {
        if (this.getUser().trim().equalsIgnoreCase("admin")) {
            if (this.getPassword().equalsIgnoreCase("admin")) {
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "\n" +
                        "Logout successfully!", ""));
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
