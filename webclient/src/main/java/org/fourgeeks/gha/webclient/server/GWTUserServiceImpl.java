package org.fourgeeks.gha.webclient.server;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fourgeeks.gha.ejb.mix.UserServiceRemote;
import org.fourgeeks.gha.webclient.client.services.GWTUserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTUserServiceImpl extends RemoteServiceServlet implements
		GWTUserService {

	// @Resource(name = "java:/jdbc/gha")
	// DataSource service;
	//
	@EJB(name = "mix.UserService")
	UserServiceRemote userService;

	private static final long serialVersionUID = 1L;

	public boolean isLogged() {
		return false;
	}

	@Override
	public boolean login(String user, String password) {
		HttpServletRequest request = this.perThreadRequest.get();
		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();

		try {
			request.login(user, password);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return false;
	}
}