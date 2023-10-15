package com.tew.model;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 55556L;

	private String login;
	private String name;

	public User(String login, String name) {
		this.setLogin(login);
		this.setName(name);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
