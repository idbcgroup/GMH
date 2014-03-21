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
	 * @return A list with all the Activity Types
	 * @throws GHAEJBException
	 */
	public List<ActivityType> getAllTypes() throws GHAEJBException;

	/**
	 * @param type
	 *            the Activity Type
	 * @return A list of Activity Subtypes for the given type
	 * @throws GHAEJBException
	 */
	public List<ActivityType> getSubType(ActivityType type)
			throws GHAEJBException;
}
