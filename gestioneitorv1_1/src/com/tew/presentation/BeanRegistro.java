package com.tew.presentation;
import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean
@SessionScoped
public class BeanRegistro implements Serializable {
	private static final long serialVersionUID = 2L;

	private String username;
	private String pass;
	private String pass2;

	@ManagedProperty(value="#{berror}")
	private BGError error;

	public BGError getError() {
		return error;
	}

	public void setError(BGError error) {
		this.error = error;
	}

	public BeanRegistro() {
		System.out.println("BeanRegistro - No existia");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	@PostConstruct
	public void init() {
		System.out.println("BeanRegistro - PostConstruct");

		// Se busca error en la request
		error = (BGError) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("error"));
		if (error == null) {
			System.out.println("BeanSettings - No existia");
			error = new BGError();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", error);
		}
	}

	public String salva() {
		try {
			if (!pass.equals(pass2)) throw new Exception("Las contraseñas no coinciden");
			if (username == null || username.equals("")) throw new Exception("El nombre de usuario no puede estar vacío");
			if (pass == null || pass.equals("")) throw new Exception("La contraseña no puede estar vacía");

			// aquí se guardaría el usuario en base de datos, pero la práctica no indica nada de eso.
			return "exito";
		} catch (Exception e) {
			getError().setView(FacesContext.getCurrentInstance().getViewRoot().getViewId());
			getError().setMethod("salva");
			getError().setClase(getClass().getName());
			getError().setMessage(e.getMessage());
			return "error";
		}
	}
}
