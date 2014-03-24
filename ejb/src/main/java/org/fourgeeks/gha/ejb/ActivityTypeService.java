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
	public void delete(List<ActivityType> entities) throws GHAEJBException {
		try {
			for (ActivityType entity : entities) {
				em.remove(entity);
			}

		} catch (final Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete the Activity Types", e);
			throw super
					.generateGHAEJBException("activityTypes-delete-fail", em);
		}

	}

	@Override
	public void delete(long id) throws GHAEJBException {
		try {
			final ActivityType entity = em.find(ActivityType.class, id);
			em.remove(entity);

		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete ActivityType", e);
			throw super.generateGHAEJBException("activityType-delete-fail", em);
		}

	}

	@Override
	public List<ActivityType> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("ActivityType.getAll",
					ActivityType.class).getResultList();
		} catch (final Exception ex) {
			String msg = "Error retrieving all the Activity Types and Subtypes";
			String messageCode = "activityType-getAll-fail";

			logger.log(Level.SEVERE, msg, ex);
			throw super.generateGHAEJBException(messageCode, em);
		}
	}

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
	public List<ActivityType> getSubTypes(ActivityType type)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("ActivityType.getSubTypes",
							ActivityType.class)
					.setParameter("parentId", type.getId()).getResultList();

		} catch (final Exception ex) {
			String msg = "Error retrieving the Activity Subtypes for the given Activity Type";
			String messageCode = "activityType-getSubType-fail";

			logger.log(Level.SEVERE, msg, ex);
			throw super.generateGHAEJBException(messageCode, em);
		}
	}

	@Override
	public ActivityType save(ActivityType entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(ActivityType.class, entity.getId());

		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving ActivityType ", e);
			throw super.generateGHAEJBException("activityType-save-fail", em);
		}
	}

}
