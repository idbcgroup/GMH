package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;

/**
 * 
 * @author caparicio
 * 
 */
@Remote
public interface RequiredResourcesServiceRemote {
	/**
	 * 
	 * @param id
	 * @throws GHAEJBException
	 */
	public void delete(long id) throws GHAEJBException;

	/**
	 * 
	 * @param requiredResources
	 * @throws GHAEJBException
	 */
	public void delete(List<RequiredResources> requiredResources)
			throws GHAEJBException;

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
	 * @param requiredResources
	 * @return the persistent entity
	 * @throws GHAEJBException
	 */
	public RequiredResources update(RequiredResources requiredResources)
			throws GHAEJBException;
}