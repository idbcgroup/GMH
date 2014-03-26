package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import java.util.List;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * 
 * @author caparicio
 * 
 */
@RemoteServiceRelativePath("requiredResources")
public interface GWTRequiredResourcesService extends RemoteService {
	/**
	 * 
	 * @param id
	 * @throws GHAEJBException
	 */
	public void delete(long id) throws GHAEJBException;

	/**
	 * Delete a list of RequiredResources from database
	 * 
	 * @param entities
	 * @throws GHAEJBException
	 * 
	 */
	public void delete(List<RequiredResources> entities) throws GHAEJBException;

	/**
	 * 
	 * @param requiredResources
	 * @return the persistent entity
	 * @throws GHAEJBException
	 */
	public RequiredResources save(RequiredResources requiredResources)
			throws GHAEJBException;

	/**
	 * 
	 * @param requiredResources
	 * @return the persistent entity
	 * @throws GHAEJBException
	 */
	public RequiredResources update(RequiredResources requiredResources)
			throws GHAEJBException;

	/**
	 * 
	 * @param resource
	 * @return the activities asociated with the serviceandresource
	 * @throws GHAEJBException
	 */
	public List<RequiredResources> findByServiceAndResource(
			ServiceAndResource resource) throws GHAEJBException;

	/**
	 * 
	 * @param activity
	 * @return the servicesandresources (EiaType) asociated with the activity
	 * @throws GHAEJBException
	 */
	public List<RequiredResources> findEiaTypeByActivity(Activity activity)
			throws GHAEJBException;

	/**
	 * 
	 * @param activity
	 * @return the servicesandresources (Material) asociated with the activity
	 * @throws GHAEJBException
	 */
	public List<RequiredResources> findMaterialByActivity(Activity activity)
			throws GHAEJBException;

	/**
	 * 
	 * @param entities
	 * @return the persistent entities
	 * @throws GHAEJBException
	 */
	public List<RequiredResources> save(List<RequiredResources> entities)
			throws GHAEJBException;
}