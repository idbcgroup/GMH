package org.fourgeeks.gha.webclient.server.rolebase;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.ejb.ess.RoleServiceRemote;
import org.fourgeeks.gha.webclient.client.rolebase.GWTRoleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */

@WebServlet(urlPatterns = { "/webclient/role" })
public class GWTRoleServiceImpl extends RemoteServiceServlet implements
		GWTRoleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "ess.RoleService")
	RoleServiceRemote service;

	@Override
	public List<Role> getAll() throws EJBException {
		return service.getAll();
	}

	@Override
	public List<Role> getAll(int offset, int size) throws EJBException {
		// TODO
		return service.getAll();
	}
}