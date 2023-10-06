package com.tew.presentation;
import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.Alumno;

@ManagedBean
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	@ManagedProperty(value="#{alumno}")
	private BeanAlumno alumno;

	@ManagedProperty(value="#{berror}")
	private BGError error;

	public void setAlumno(Alumno alumno) {
		this.alumno = (BeanAlumno) alumno;
	}
	public BeanAlumno getAlumno(){
		return this.alumno;
	}

	public void setError(BGError error) {
		this.error = error;
	}

	public BGError getError(){
		return this.error;
	}

	//Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera creado
	//y en caso contrario se crea.
	//(hay que tener en cuenta que es un Bean de sesión)
	//Se usa @PostConstruct, ya que en el contructor no se sabe todavía si
	//el MBean ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
		// Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
		alumno = (BeanAlumno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("alumno"));
		// si no existe lo creamos e inicializamos
		if (alumno == null) {
			System.out.println("BeanSettings - No existia");
			alumno = new BeanAlumno();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("alumno", alumno);
		}

		// Se busca error en la request
		error = (BGError) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("error"));
		if (error == null) {
			System.out.println("BeanSettings - No existia");
			error = new BGError();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", error);
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

	public Locale getLocale() {
		return(locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (alumno != null)
				if (alumno.getId()== null) //valores por defecto del alumno, si no NO inicializar
					alumno.iniciaAlumno(null);
		} catch (Exception ex) {
			getError().setView("");
			getError().setMethod("setSpanish");
			getError().setClase(this.getClass().getName());
			getError().setMessage(ex.getMessage());
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (alumno != null)
				if (alumno.getId()== null) //valores por defecto del alumno, si no NO inicializar
					alumno.iniciaAlumno(null);
		} catch (Exception ex){
			getError().setView("");
			getError().setMethod("setEnglish");
			getError().setClase(this.getClass().getName());
			getError().setMessage(ex.getMessage());
		}
	}
}
