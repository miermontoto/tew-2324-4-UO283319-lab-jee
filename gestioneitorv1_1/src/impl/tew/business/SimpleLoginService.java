package impl.tew.business;

import com.tew.model.User;

public class SimpleLoginService implements com.tew.business.LoginService {

	@Override
	public User verify(String login, String password) {
		if (!validLogin(login, password)) return null;
		return new User(login, "Sudo MX");
	}

	@Override
	public boolean validLogin(String login, String password) {
		return login.equals("admin") && password.equals("tew");
	}
}
