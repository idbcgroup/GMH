/**
 * 
 */
package org.fourgeeks.gha.webclient.server.ssouser;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote;
import org.fourgeeks.gha.ejb.gar.BpuFunctionServiceRemote;
import org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/ssouser" })
public class GWTSSOUserServiceImpl extends RemoteServiceServlet implements
		GWTSSOUserService {
	@EJB(name = "ess.SSOUserService")
	SSOUserServiceRemote service;

	@EJB(name = "gar.BpuFunctionService")
	BpuFunctionServiceRemote bpuFunctionService;

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
		service.delete(Id);
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
		return service.find(ssoUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService#find(long)
	 */
	@Override
	public SSOUser find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.ssouser.GWTSSOUserService#getAll()
	 */
	@Override
	public List<SSOUser> getAll() throws GHAEJBException {
		return service.getAll();
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
		return service.save(ssoUser);
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
		return service.update(ssoUser);
	}

	@Override
	public BpuFunction save(BpuFunction bpuFunction) throws GHAEJBException {
		return service.save(bpuFunction);// TODO pasar a GWTBpuFunction
	}

	@Override
	public void delete(BpuFunction bpuFunction) throws GHAEJBException {
		service.delete(bpuFunction);// TODO pasar a GWTBpuFunction
	}

	@Override
	public List<BpuFunction> getFunctionsByBpu(Bpu bpu) throws GHAEJBException {
		return bpuFunctionService.getFunctionsByBpu(bpu); // TODO pasar a
															// GwtBpuFunction
	}

}
