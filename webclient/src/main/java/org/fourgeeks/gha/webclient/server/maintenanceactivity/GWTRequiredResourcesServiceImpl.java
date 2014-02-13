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
		return service.save(requiredResources);
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
	 * .GWTRequiredResourcesService#delete(java.util.List)
	 */
	@Override
	public void delete(List<RequiredResources> entities) throws GHAEJBException {
		service.delete(entities);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource
	 * .
	 * GWTRequiredResourcesService#findEiaTypeByActivity(org.fourgeeks.gha.domain
	 * .Activity)
	 */
	@Override
	public List<RequiredResources> findEiaTypeByActivity(Activity activity)
			throws GHAEJBException {
		return service.findEiaTypeByActivity(activity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource
	 * .
	 * GWTRequiredResourcesService#findMaterialByActivity(org.fourgeeks.gha.domain
	 * .Activity)
	 */
	@Override
	public List<RequiredResources> findMaterialByActivity(Activity activity)
			throws GHAEJBException {
		return service.findMaterialByActivity(activity);
	}

	@Override
	public RequiredResources update(RequiredResources requiredResources)
			throws GHAEJBException {
		return service.update(requiredResources);
	}
}
