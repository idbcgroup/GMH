package org.fourgeeks.gha.webclient.server;

import org.fourgeeks.gha.webclient.client.GWTUserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTUserServiceImpl extends RemoteServiceServlet implements
		GWTUserService {

	// @EJB(name = "UserService")
	// UserServiceRemote userService;

	private static final long serialVersionUID = 1L;

	@Override
	public boolean test() {
		// TODO Auto-generated method stub
		return false;
	}
}
