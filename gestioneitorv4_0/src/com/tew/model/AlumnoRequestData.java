package com.tew.model;

import javax.xml.bind.annotation.XmlElement;

public class AlumnoRequestData extends Alumno {
	private String token;

	@XmlElement
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
