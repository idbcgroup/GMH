/**
 * 
 */
package org.fourgeeks.gha.webclient.server.ssouser;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.ess.auth.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.ess.ui.PermissionBpuServiceRemote;
import org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/ssouser" })
public class GWTSSOUserServiceImpl extends RemoteServiceServlet implements
		GWTSSOUserService {
	@EJB(lookup = "java:global/ear-1/ejb-1/SSOUserService")
	SSOUserServiceRemote ssoUserService;

	@EJB(lookup = "java:global/ear-1/ejb-1/AppFormViewFunctionBpuService")
	PermissionBpuServiceRemote bpuFunctionService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		ssoUserService.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService#find(org
	 * .fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public List<SSOUser> find(SSOUser ssoUser) throws GHAEJBException {
		return ssoUserService.find(ssoUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService#find(long)
	 */
	@Override
	public SSOUser find(long Id) throws GHAEJBException {
		return ssoUserService.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService#getAll()
	 */
	@Override
	public List<SSOUser> getAll() throws GHAEJBException {
		return ssoUserService.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService#save(org
	 * .fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public SSOUser save(SSOUser ssoUser) throws GHAEJBException {
		return ssoUserService.save(ssoUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService#update(org
	 * .fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public SSOUser update(SSOUser ssoUser) throws GHAEJBException {
		return ssoUserService.update(ssoUser);
	}

	@Override
	public void delete(List<SSOUser> entities) throws GHAEJBException {
		for (final SSOUser entity : entities)
			delete(entity.getId());

	}

}
