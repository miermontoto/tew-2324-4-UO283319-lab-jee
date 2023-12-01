package impl.tew.business.resteasy;

public class LoginServiceRsImpl implements LoginServiceRs {
	@Override
	public String login(User user) {
		if (Factories.services.createLoginService().verify(user.getLogin(), user.getPassword()) != null)
			return GestorSesion.getInstance().registrarLogin(user.getLogin());
		return "";
	}
}
