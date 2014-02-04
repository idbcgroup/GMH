/**
 * 
 */
package org.fourgeeks.gha.webclient.server.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.ejb.gmh.ServiceAndResourceServiceRemote;
import org.fourgeeks.gha.webclient.client.resource.GWTServiceResourceService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 * 
 */
@WebServlet(urlPatterns = { "/webclient/serviceResource" })
public class GWTServiceAndResourceServiceImpl extends RemoteServiceServlet
		implements GWTServiceResourceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(lookup = "java:global/ear-1/ejb-1/ServiceAndResourceService")
	ServiceAndResourceServiceRemote ejbService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.resource.GWTResourceService#delete
	 * (long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		ejbService.delete(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#
	 * findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<ServiceAndResource> findByActivity(Activity activity)
			throws GHAEJBException {
		return ejbService.findByActivity(activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.resource.GWTResourceService#find(long)
	 */
	@Override
	public ServiceAndResource find(long Id) throws GHAEJBException {
		return ejbService.find(Id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.resource.GWTResourceService#getAll()
	 */
	@Override
	public List<ServiceAndResource> getAll() throws GHAEJBException {
		return ejbService.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.resource.GWTResourceService#getAll
	 * (int, int)
	 */
	@Override
	public List<ServiceAndResource> getAll(int offset, int size)
			throws GHAEJBException {
		return ejbService.getAll(offset, size);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.resource.GWTResourceService#save(org
	 * .fourgeeks.gha.domain.gmh.RaS)
	 */
	@Override
	public ServiceAndResource save(ServiceAndResource ras)
			throws GHAEJBException {
		return ejbService.save(ras);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.resource.GWTResourceService#update
	 * (org.fourgeeks.gha.domain.gmh.RaS)
	 */
	@Override
	public ServiceAndResource update(ServiceAndResource ras)
			throws GHAEJBException {
		return ejbService.update(ras);
	}

}
