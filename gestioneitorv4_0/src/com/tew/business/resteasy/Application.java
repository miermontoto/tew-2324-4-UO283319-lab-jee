//Registro de clases punto final.
//Es una forma de registro est�tico de clases punto final (servicios rest)
package com.tew.business.resteasy;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import impl.tew.business.resteasy.AlumnosServicesRsImpl;
import impl.tew.business.resteasy.LoginServiceRsImpl;
@SuppressWarnings("unchecked")
public class Application extends javax.ws.rs.core.Application {
	private Set<Class<?>> classes = new HashSet<Class<?>>();

	public Application() {
		classes.add(AlumnosServicesRsImpl.class);
		classes.add(LoginServiceRsImpl.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		return Collections.EMPTY_SET;
	}
}
