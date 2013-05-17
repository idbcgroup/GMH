package org.fourgeeks.gha.webclient.server;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.mix.UserServiceRemote;
import org.fourgeeks.gha.webclient.client.GWTUserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTUserServiceImpl extends RemoteServiceServlet implements
		GWTUserService {

	// @Resource(name = "java:/jdbc/gha")
	// DataSource service;
	//
	@EJB(name = "mix.UserService")
	UserServiceRemote userService;

	private static final long serialVersionUID = 1L;

	@Override
	public boolean test() {
		return userService.test();
	}

	@Override
	public LegalEntity test2() {
		return userService.test2();
	}

}
