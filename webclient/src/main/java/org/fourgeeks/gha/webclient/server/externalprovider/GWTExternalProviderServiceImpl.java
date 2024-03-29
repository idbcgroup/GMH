/**
 * 
 */
package org.fourgeeks.gha.webclient.server.externalprovider;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote;
import org.fourgeeks.gha.webclient.client.externalprovider.GWTExternalProviderService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author alacret
 * 
 */
@WebServlet(urlPatterns = { "/webclient/externalProvider" })
public class GWTExternalProviderServiceImpl extends RemoteServiceServlet
		implements GWTExternalProviderService {

	@EJB(lookup = "java:global/ear-1/ejb-1/ExternalProviderService")
	ExternalProviderServiceRemote service;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<ExternalProvider> getAll() throws GHAEJBException {
		return service.getAll();
	}

	@Override
	public List<ExternalProvider> getAll(int offset, int size)
			throws GHAEJBException {
		return service.getAll(); // TODO
	}
}
