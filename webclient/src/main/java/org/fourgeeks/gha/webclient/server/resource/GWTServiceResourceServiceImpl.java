/**
 * 
 */
package org.fourgeeks.gha.webclient.server.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote;
import org.fourgeeks.gha.webclient.client.resource.GWTServiceResourceService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/serviceResource"})
public class GWTServiceResourceServiceImpl extends RemoteServiceServlet implements GWTServiceResourceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.serviceResourceService")
	ServiceResourceServiceRemote ejbService;

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		ejbService.delete(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<ServiceResource> findByProtocolActivity(
			MaintenanceActivity protocolActivity) throws EJBException {
		return ejbService.findByProtocolActivity(protocolActivity);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#find(long)
	 */
	@Override
	public ServiceResource find(long Id) throws EJBException {
		return ejbService.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#getAll()
	 */
	@Override
	public List<ServiceResource> getAll() throws EJBException {
		return ejbService.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#getAll(int, int)
	 */
	@Override
	public List<ServiceResource> getAll(int offset, int size) throws EJBException {
		return ejbService.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#save(org.fourgeeks.gha.domain.gmh.RaS)
	 */
	@Override
	public ServiceResource save(ServiceResource ras) throws EJBException {
		return ejbService.save(ras);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#update(org.fourgeeks.gha.domain.gmh.RaS)
	 */
	@Override
	public ServiceResource update(ServiceResource ras) throws EJBException {
		return ejbService.update(ras);
	}

}
