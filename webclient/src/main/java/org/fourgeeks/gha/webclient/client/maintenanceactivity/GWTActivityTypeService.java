/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.List;

import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author emiliot
 * 
 */

@RemoteServiceRelativePath("activityTypeService")
public interface GWTActivityTypeService extends RemoteService {
	/**
	 * 
	 * @param id
	 * @throws GHAEJBException
	 */
	public void delete(long id) throws GHAEJBException;

	/**
	 * 
	 * @param entities
	 * @throws GHAEJBException
	 */
	public void delete(List<ActivityType> entities) throws GHAEJBException;

	/**
	 * @return A list with all the Activity Types that dont have parents
	 * @throws GHAEJBException
	 */
	public List<ActivityType> getAllTypes() throws GHAEJBException;

	/**
	 * @return A list with all the Activity Types and Sub Types
	 * @throws GHAEJBException
	 */
	public List<ActivityType> getAll() throws GHAEJBException;

	/**
	 * @param type
	 *            the Activity Type
	 * @return A list of Activity Subtypes for the given type
	 * @throws GHAEJBException
	 */
	public List<ActivityType> getSubTypes(ActivityType type)
			throws GHAEJBException;

	/**
	 * 
	 * @param entity
	 * @return the saved Activity Type or Subtype
	 * @throws GHAEJBException
	 */
	public ActivityType save(ActivityType entity) throws GHAEJBException;
}
