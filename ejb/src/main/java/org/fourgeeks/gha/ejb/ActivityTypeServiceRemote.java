package org.fourgeeks.gha.ejb;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * @author naramirez
 * 
 */
@Remote
public interface ActivityTypeServiceRemote {
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
	 * @return ActivityType
	 * @throws GHAEJBException
	 */
	public ActivityType save(ActivityType entity) throws GHAEJBException;
}
