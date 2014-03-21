package org.fourgeeks.gha.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;

/**
 * Session Bean implementation class ActivityTypeService
 * 
 * @author naramirez
 */
@Stateless
public class ActivityTypeService extends GHAEJBExceptionService implements
		ActivityTypeServiceRemote {

	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ActivityTypeService.class.getName());

	@Override
	public List<ActivityType> getAllTypes() throws GHAEJBException {
		try {
			return em.createNamedQuery("ActivityType.getAllTypes",
					ActivityType.class).getResultList();
		} catch (final Exception ex) {
			String msg = "Error retrieving all the Activity Types";
			String messageCode = "activityType-getAllTypes-fail";

			logger.log(Level.SEVERE, msg, ex);
			throw super.generateGHAEJBException(messageCode, em);
		}
	}

	@Override
	public List<ActivityType> getSubType(ActivityType type)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("ActivityType.getSubType",
							ActivityType.class)
					.setParameter("parentId", type.getId()).getResultList();

		} catch (final Exception ex) {
			String msg = "Error retrieving the Activity Subtypes for the given Activity Type";
			String messageCode = "activityType-getSubType-fail";

			logger.log(Level.SEVERE, msg, ex);
			throw super.generateGHAEJBException(messageCode, em);
		}
	}

}
