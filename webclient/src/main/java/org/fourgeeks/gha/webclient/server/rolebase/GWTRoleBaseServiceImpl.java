package org.fourgeeks.gha.webclient.server.rolebase;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.ejb.ess.RoleBaseServiceRemote;
import org.fourgeeks.gha.webclient.client.rolebase.GWTRoleBaseService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
public class GWTRoleBaseServiceImpl extends RemoteServiceServlet implements
		GWTRoleBaseService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "ess.RoleBaseService")
	RoleBaseServiceRemote service;

	@Override
	public List<RoleBase> getAll() throws EJBException {
		return service.getAll();
	}

	@Override
	public List<RoleBase> getAll(int offset, int size) throws EJBException {
		// TODO
		return service.getAll();
	}
}