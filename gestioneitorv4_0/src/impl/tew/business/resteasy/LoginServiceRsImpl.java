package impl.tew.business.resteasy;

import com.tew.business.resteasy.LoginServiceRs;
import com.tew.infrastructure.Factories;
import com.tew.infrastructure.GestorSesion;
import com.tew.model.User;

public class LoginServiceRsImpl implements LoginServiceRs {
	@Override
	public String login(User user) {
		if (Factories.services.createLoginService().verify(user.getLogin(), user.getPassword()) != null)
			return GestorSesion.getInstance().registrarLogin(user.getLogin());
		return "";
	}
}
