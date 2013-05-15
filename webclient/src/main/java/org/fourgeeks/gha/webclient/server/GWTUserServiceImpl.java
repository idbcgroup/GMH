package org.fourgeeks.gha.webclient.server;

import java.sql.PreparedStatement;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.sql.DataSource;

import org.fourgeeks.gha.ejb.mix.UserServiceRemote;
import org.fourgeeks.gha.webclient.client.GWTUserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class GWTUserServiceImpl extends RemoteServiceServlet implements
		GWTUserService {

	@Resource(name = "java:/jdbc/gha")
	DataSource service;

	@EJB(name = "mix.UserService")
	UserServiceRemote userService;

	private static final long serialVersionUID = 1L;

	@Override
	public boolean test() {
		try {
			PreparedStatement prepareStatement = service.getConnection()
					.prepareStatement("SELECT 1");
			prepareStatement.execute();
			prepareStatement.getResultSet();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return userService.test();
	}
}
