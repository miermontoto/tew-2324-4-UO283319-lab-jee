package com.tew.business;

import java.util.List;

import com.tew.model.User;

public interface LoginService {

	User verify(String username, String pass);
	boolean validLogin(String username, String pass);

}
