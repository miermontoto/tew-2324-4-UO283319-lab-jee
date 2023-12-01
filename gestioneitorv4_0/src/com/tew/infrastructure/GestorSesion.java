package com.tew.infrastructure;

public class GestorSesion {
	private static GestorSesion instance;
	private Map<String, String> logins = new HashMap<>();

	private GestorSesion() {}

	public static GestorSesion getInstance() {
		if (instance == null)
			instance = new GestorSesion();
		return instance;
	}

	public String registrarLogin(String name) {
		String token = UUID.randomUUID().toString();
		logins.put(token, name);
		return token;
	}

	public String comprobarToken(String token) {
		return logins.getOrDefault(token, null);
	}
}
