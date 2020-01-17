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

    public String autenticar()
    {
        if (this.getUser().trim().equalsIgnoreCase("ADMIN") && this.getPassword().equalsIgnoreCase("ADMIN"))
            return "/cadastro/paciente/paciente?faces-redirect=true";
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Incorrect Username and Passowrd", "Please enter correct username and Password"));
            return "";
        }
    }

    public String getPassword()
    {
        return password;
    }

    public String getUser()
    {
        return user;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

}
