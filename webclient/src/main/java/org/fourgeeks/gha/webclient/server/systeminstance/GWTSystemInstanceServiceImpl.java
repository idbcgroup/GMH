/**
 * 
 */
package org.fourgeeks.gha.webclient.server.systeminstance;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.SystemInstance;
import org.fourgeeks.gha.ejb.mix.SystemInstanceServiceRemote;
import org.fourgeeks.gha.webclient.client.systeminstance.GWTSystemInstanceService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/systemInstance" })
public class GWTSystemInstanceServiceImpl extends RemoteServiceServlet
		implements GWTSystemInstanceService {
	@EJB(lookup = "java:global/ear-1/ejb-1/SystemInstanceService")
	SystemInstanceServiceRemote service;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.systeminstance.GWTSystemInstanceService
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
	 * org.fourgeeks.gha.webclient.client.systeminstance.GWTSystemInstanceService
	 * #find(org.fourgeeks.gha.domain.mix.SystemInstance)
	 */
	@Override
	public List<SystemInstance> find(SystemInstance systemInstance)
			throws GHAEJBException {
		return service.find(systemInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.systeminstance.GWTSystemInstanceService
	 * #find(long)
	 */
	@Override
	public SystemInstance find(long Id) throws GHAEJBException {
		return service.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.systeminstance.GWTSystemInstanceService
	 * #getAll()
	 */
	@Override
	public List<SystemInstance> getAll() throws GHAEJBException {
		return service.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.systeminstance.GWTSystemInstanceService
	 * #save(org.fourgeeks.gha.domain.mix.SystemInstance)
	 */
	@Override
	public SystemInstance save(SystemInstance systemInstance)
			throws GHAEJBException {
		return service.save(systemInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.systeminstance.GWTSystemInstanceService
	 * #update(org.fourgeeks.gha.domain.mix.SystemInstance)
	 */
	@Override
	public SystemInstance update(SystemInstance systemInstance)
			throws GHAEJBException {
		return service.save(systemInstance);
	}

}
