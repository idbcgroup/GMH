package org.fourgeeks.gha.webclient.server.baserole;

import java.util.List;

import javax.ejb.EJB;

import org.fourgeeks.gha.domain.ess.BaseRole;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.ejb.ess.BaseRoleServiceRemote;
import org.fourgeeks.gha.webclient.client.baserole.GWTBaseRoleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
public class GWTBaseRoleServiceImpl extends RemoteServiceServlet implements
		GWTBaseRoleService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "ess.BaseRoleService")
	BaseRoleServiceRemote service;

	@Override
	public List<BaseRole> getAll() throws EJBException {
		return service.getAll();
	}

	@Override
	public List<BaseRole> getAll(int offset, int size) throws EJBException {
		// TODO
		return service.getAll();
	}
}