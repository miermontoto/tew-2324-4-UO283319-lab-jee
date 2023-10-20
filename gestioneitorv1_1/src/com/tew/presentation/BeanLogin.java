package com.tew.presentation;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.LoginService;
import com.tew.infrastructure.Factories;
import com.tew.model.User;

@ManagedBean(name = "login")
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 2L;

	private String name = "";
	private String password = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public String verify() {
		// necesario para acceder a msgs y a los mensajes en español e inglés de los ficheros
		// de propiedades.
		FacesContext jsfCtx = FacesContext.getCurrentInstance();
		ResourceBundle bundle = jsfCtx.getApplication().getResourceBundle(jsfCtx, "msgs");

		FacesMessage msgs = null;
		LoginService login = Factories.services.createLoginService();

		User user = login.verify(name, password);
		if (user != null) {
			putUserInSession(user);
			return "success";
		}

		// Si el usuario no se encuentra, se prepara el mensaje que saldrá en la vista.
		msgs = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error en el login", null);
		jsfCtx.addMessage(null, msgs);

		return "login";
	}

	private void putUserInSession(User user) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("LOGGEDIN_USER", user);
	}
}
