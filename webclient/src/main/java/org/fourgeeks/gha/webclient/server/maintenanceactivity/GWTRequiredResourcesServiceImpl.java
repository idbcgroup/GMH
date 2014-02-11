package org.fourgeeks.gha.webclient.server.maintenanceactivity;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.ejb.gmh.RequiredResourcesServiceRemote;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource.GWTRequiredResourcesService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author caparicio
 * 
 */
@WebServlet(urlPatterns = { "/webclient/requiredResources" })
public class GWTRequiredResourcesServiceImpl extends RemoteServiceServlet
		implements GWTRequiredResourcesService {

	private static final long serialVersionUID = -5364919525539569425L;

	@EJB(lookup = "java:global/ear-1/ejb-1/RequiredResourcesService!"
			+ "org.fourgeeks.gha.ejb.gmh.RequiredResourcesServiceRemote")
	RequiredResourcesServiceRemote service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource
	 * .GWTRequiredResourcesService#delete(long)
	 */
	@Override
	public void delete(long id) throws GHAEJBException {
		service.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource
	 * .GWTRequiredResourcesService#save(org.fourgeeks.gha.domain.gmh.
	 * RequiredResources)
	 */
	@Override
	public RequiredResources save(RequiredResources requiredResources)
			throws GHAEJBException {
		try {
			System.out.println("Aquí entró 1");
			return service.save(requiredResources);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource
	 * .
	 * GWTRequiredResourcesService#findByServiceAndResource(org.fourgeeks.gha.domain
	 * .gmh.ServiceAndResource)
	 */
	@Override
	public List<RequiredResources> findByServiceAndResource(
			ServiceAndResource resource) throws GHAEJBException {
		return service.findByServiceAndResource(resource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource
	 * .
	 * GWTRequiredResourcesService#findByActivity(org.fourgeeks.gha.domain.Activity
	 * )
	 */
	@Override
	public List<RequiredResources> findByActivity(Activity activity)
			throws GHAEJBException {
		return service.findByActivity(activity);
	}
}
