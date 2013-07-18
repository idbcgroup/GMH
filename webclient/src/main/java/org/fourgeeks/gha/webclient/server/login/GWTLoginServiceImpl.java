package org.fourgeeks.gha.webclient.server.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fourgeeks.gha.webclient.client.login.GWTLoginService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTLoginServiceImpl extends RemoteServiceServlet implements
		GWTLoginService {

	// @Resource(name = "java:/jdbc/gha")
	// DataSource service;
	//
	// @EJB(name = "mix.UserService")
	// UserServiceRemote userService;

	private static final long serialVersionUID = 1L;

	public boolean isLogged() {
		return false;
	}

	@Override
	public boolean login(String user, String password)
			throws IllegalArgumentException {
		HttpServletRequest request = this.perThreadRequest.get();

		if (user.equals("") || password.equals(""))
			throw new IllegalArgumentException(
					"Debe indicar usuario y contraseña");

		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();

		try {
			request.login(user, password);
			return true;
		} catch (Exception e) {
			System.out.println("aca se deberia capturar el error"
					+ e.getMessage());
			return false;
		}
	}
}