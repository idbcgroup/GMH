/**
 * 
 */
package org.fourgeeks.gha.webclient.server.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.Resource;
import org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote;
import org.fourgeeks.gha.webclient.client.resource.GWTResourceService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author emiliot
 *
 */
@WebServlet(urlPatterns = {"/webclient/resourceService"})
public class GWTResourceServiceImpl extends RemoteServiceServlet implements GWTResourceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB(name = "gmh.ResourceService")
	ResourceServiceRemote ejbService;

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
	public List<Resource> findByProtocolActivity(
			ProtocolActivity protocolActivity) throws EJBException {
		return ejbService.findByProtocolActivity(protocolActivity);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#find(long)
	 */
	@Override
	public Resource find(long Id) throws EJBException {
		return ejbService.find(Id);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#getAll()
	 */
	@Override
	public List<Resource> getAll() throws EJBException {
		return ejbService.getAll();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#getAll(int, int)
	 */
	@Override
	public List<Resource> getAll(int offset, int size) throws EJBException {
		return ejbService.getAll(offset, size);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#save(org.fourgeeks.gha.domain.gmh.Resource)
	 */
	@Override
	public Resource save(Resource resource) throws EJBException {
		return ejbService.save(resource);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.resource.GWTResourceService#update(org.fourgeeks.gha.domain.gmh.Resource)
	 */
	@Override
	public Resource update(Resource resource) throws EJBException {
		return ejbService.update(resource);
	}

}
