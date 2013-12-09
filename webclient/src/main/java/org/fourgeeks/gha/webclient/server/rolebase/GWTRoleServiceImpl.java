package org.fourgeeks.gha.webclient.server.rolebase;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
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
	// @EJB(lookup =
	// "java:jboss/exported/ear-1/ejb-1/RoleService!org.fourgeeks.gha.ejb.ess.RoleServiceRemote")
	// (lookup =
	// "java:module/RoleService!org.fourgeeks.gha.ejb.ess.RoleServiceRemote")
	// java:jboss/exported/ear-1/ejb-1/RoleService!org.fourgeeks.gha.ejb.ess.RoleServiceRemote
	// java:global/ear-1/ejb-1/RoleService
	// java:app/ejb-1/RoleService
	// java:module/RoleService
	@EJB(lookup = "java:global/ear-1/ejb-1/RoleService")
	RoleServiceRemote service;

	@Override
	public List<Role> getAll() throws GHAEJBException {
		return service.getAll();
	}

	@Override
	public List<Role> getAll(int offset, int size) throws GHAEJBException{
		return service.getAll();
	}
}