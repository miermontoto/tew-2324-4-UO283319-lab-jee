package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.tew.business.AlumnosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Alumno;

@SessionScoped
public class BeanAlumnos implements Serializable {
	private static final long serialVersionUID = 55555L;

	@ManagedProperty(value="#{alumno}")
	private BeanAlumno alumno;

	@ManagedProperty(value="#{berror}")
	private BGError error;

	private Alumno[] alumnos = null;

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

	//Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	//y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
	//Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
	//ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanAlumnos - PostConstruct");
		//Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
		alumno = (BeanAlumno) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("alumno"));
		//si no existe lo creamos e inicializamos
		if (alumno == null) {
			System.out.println("BeanAlumnos - No existia");
			alumno = new BeanAlumno();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("alumno", alumno);
		}

		error = (BGError) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("error"));

		if (error == null) {
			error = new BGError();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("error", error);
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanAlumnos - PreDestroy");
	}

	public void iniciaAlumno(ActionEvent event) {
		ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msgs");
		alumno.setId(null);
		alumno.setIduser(bundle.getString("valorDefectoUserId"));
		alumno.setNombre(bundle.getString("valorDefectoNombre"));
		alumno.setApellidos(bundle.getString("valorDefectoApellidos"));
		alumno.setEmail(bundle.getString("valorDefectoCorreo"));
	}

	public String listado() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a través de la factoría
			service = Factories.services.createAlumnosService();
			// Asi le damos información a toArray para poder hacer el casting a Alumno[]
			alumnos = (Alumno []) service.getAlumnos().toArray(new Alumno[0]);
			System.out.println("bbb");
			return "exito";
		} catch (Exception e) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en " + getClass().getName() + ":listado()", null);
			FacesContext.getCurrentInstance().addMessage("error_sample", msgs);
			System.out.println("aaa");
			return "";
		}
	}

	public String edit() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a través de la factoría
			service = Factories.services.createAlumnosService();
			// Recargamos el alumno en la tabla de la base de datos por si hubiera cambios.
			alumno = (BeanAlumno) service.findById(alumno.getId());
			return "exito";
		} catch (Exception e) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error en " + getClass().getName() + ":edit()", null);
			FacesContext.getCurrentInstance().addMessage("error_sample", msgs);
			return "";
		}
	}

	public String salva() {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a través de la factoría
			service = Factories.services.createAlumnosService();
			// Salvamos o actualizamos el alumno segun sea una operacion de alta o de edición
			if (alumno.getId() == null) {
				service.saveAlumno(alumno);
			} else {
				service.updateAlumno(alumno);
			}
			// Actualizamos el javabean de alumnos inyectado en la tabla
			alumnos = (Alumno []) service.getAlumnos().toArray(new Alumno[0]);
			return "exito";
		} catch (Exception e) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error en " + getClass().getName() + ":salva()", null);
			FacesContext.getCurrentInstance().addMessage("error_sample", msgs);
			return "";
		}
	}

	public String baja(Alumno alumno) {
		AlumnosService service;
		try {
			// Acceso a la implementacion de la capa de negocio
			// a través de la factoría
			service = Factories.services.createAlumnosService();
			service.deleteAlumno(alumno.getId());
			// Actualizamos el javabean de alumnos inyectado en la tabla
			alumnos = (Alumno []) service.getAlumnos().toArray(new Alumno[0]);
			return "exito";
		} catch (Exception e) {
			FacesMessage msgs = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error en " + getClass().getName() + ":baja()", null);
			FacesContext.getCurrentInstance().addMessage("error_sample", msgs);
			return "";
		}
	}

	public Alumno[] getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Alumno[] alumnos) {
		this.alumnos = alumnos;
	}
}
