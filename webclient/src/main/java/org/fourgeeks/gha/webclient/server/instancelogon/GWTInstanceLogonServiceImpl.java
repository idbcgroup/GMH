/**
 * 
 */
package org.fourgeeks.gha.webclient.server.instancelogon;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.ess.auth.InstanceLogon;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.ess.auth.InstanceLogonServiceRemote;
import org.fourgeeks.gha.webclient.client.instancelogon.GWTInstanceLogonService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/instanceLogon" })
public class GWTInstanceLogonServiceImpl extends RemoteServiceServlet implements
		GWTInstanceLogonService {
	@EJB(lookup = "java:global/ear-1/ejb-1/InstanceLogonService")
	InstanceLogonServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.instancelogon.GWTInstanceLogonService
	 * #delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		service.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.instancelogon.GWTInstanceLogonService
	 * #getAll()
	 */
	@Override
	public List<InstanceLogon> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.instancelogon.GWTInstanceLogonService
	 * #save(org.fourgeeks.gha.domain.ess.InstanceLogon)
	 */
	@Override
	public InstanceLogon save(InstanceLogon instanceLogon)
			throws GHAEJBException {
		return service.save(instanceLogon);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.instancelogon.GWTInstanceLogonService
	 * #update(org.fourgeeks.gha.domain.ess.InstanceLogon)
	 */
	@Override
	public InstanceLogon update(InstanceLogon instanceLogon)
			throws GHAEJBException {
		return service.update(instanceLogon);
	}

}
