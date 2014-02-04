/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class SubProtocolAndCheklistService extends GHAEJBExceptionService
		implements SubProtocolAndCheklistServiceRemote,
		SubProtocolAndCheklistServiceLocal {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(SubProtocolAndCheklistService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#delete(
	 * long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			SubProtocolAndChecklist entity = em.find(
					SubProtocolAndChecklist.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete MaintenanceSubProtocol", e);
			throw super.generateGHAEJBException(
					"maintenanceSubProtocol-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.SubProtocolAndCheklistServiceRemote#delete(
	 * java.util.List)
	 */
	@Override
	public void delete(List<SubProtocolAndChecklist> entities)
			throws GHAEJBException {
		try {
			if (entities.isEmpty())
				return;

			// elimino las activiades seleccionadas
			Activity activity = entities.get(0).getParentActivity();
			for (SubProtocolAndChecklist entity : entities) {
				SubProtocolAndChecklist ent = em.find(
						SubProtocolAndChecklist.class, entity.getId());
				em.remove(ent);
			}

			// actualizo el orden de las actividades restantes
			final List<SubProtocolAndChecklist> remainEntities = findByParentActivity(activity);
			for (int i = 0, ord = 1, size = remainEntities.size(); i < size; i++, ord++) {
				SubProtocolAndChecklist entity = remainEntities.get(i);
				entity.setOrdinal(ord);
				em.merge(entity);
			}
			em.flush(); // sincronizo con la BD

		} catch (Exception e) {
			final String msgError = "ERROR: unable to delete the list of SubProtocolAndChecklist";
			logger.log(Level.INFO, msgError, e);

			final String messageCode = "maintenanceSubProtocol-delete-fail";
			throw super.generateGHAEJBException(messageCode,
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#
	 * findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<SubProtocolAndChecklist> findByParentActivity(Activity activity)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"SubProtocolAndChecklist.findByParentActivity",
							SubProtocolAndChecklist.class)
					.setParameter("activity", activity).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding sub-protocol activities by activity", e);
			throw super.generateGHAEJBException(
					"maintenanceSubProtocol-findByMaintenanceActvity-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#find(long)
	 */
	@Override
	public SubProtocolAndChecklist find(long Id) throws GHAEJBException {
		try {
			return em.find(SubProtocolAndChecklist.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaintenanceSubProtocol", e);
			throw super.generateGHAEJBException(
					"maintenanceSubProtocol-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#getAll()
	 */
	@Override
	public List<SubProtocolAndChecklist> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("SubProtocolAndChecklist.getAll",
					SubProtocolAndChecklist.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceSubProtocol",
					e);
			throw super.generateGHAEJBException(
					"maintenanceSubProtocol-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<SubProtocolAndChecklist> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("SubProtocolAndChecklist.getAll",
							SubProtocolAndChecklist.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceSubProtocol",
					e);
			throw super.generateGHAEJBException(
					"maintenanceSubProtocol-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#save(org
	 * .fourgeeks.gha.domain.gmh.MaintenanceSubProtocol)
	 */
	@Override
	public SubProtocolAndChecklist save(
			SubProtocolAndChecklist maintenanceSubProtocol)
			throws GHAEJBException {
		try {
			em.persist(maintenanceSubProtocol);
			em.flush();
			return em.find(SubProtocolAndChecklist.class,
					maintenanceSubProtocol.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenanceSubProtocol ", e);
			throw super.generateGHAEJBException(
					"maintenanceSubProtocol-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#update(
	 * org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol)
	 */
	@Override
	public SubProtocolAndChecklist update(
			SubProtocolAndChecklist maintenanceSubProtocol)
			throws GHAEJBException {
		try {
			em.merge(maintenanceSubProtocol);
			em.flush();
			return em.find(SubProtocolAndChecklist.class,
					maintenanceSubProtocol.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: updating MaintenanceSubProtocol ", e);
			throw super.generateGHAEJBException(
					"maintenanceSubProtocol-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceLocal#
	 * getSubProtocolActivitiesCount
	 * (org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long getSubProtocolActivitiesCount(Activity activity)
			throws GHAEJBException {
		try {
			String stringQuery = "SELECT COUNT(e) FROM SubProtocolAndChecklist e WHERE e.parentActivity = :activity";
			Long result = em.createQuery(stringQuery, Long.class)
					.setParameter("activity", activity).getSingleResult();

			return result;
		} catch (Exception e) {
			String stringMsg = "Error: geting the number of activities associated to the given activity sub-protocol";
			logger.log(Level.INFO, stringMsg, e);

			String messageCode = "maintenanceSubProtocol-getSubProtocolActivitiesCount-fail";
			throw super.generateGHAEJBException(messageCode,
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceLocal#
	 * getSubProtocolCost(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BigDecimal getSubProtocolCost(Activity activity)
			throws GHAEJBException {
		try {
			String namedQuery = "SubProtocolAndChecklist.findByParentActivity";
			List<SubProtocolAndChecklist> subProtocol = em
					.createNamedQuery(namedQuery, SubProtocolAndChecklist.class)
					.setParameter("activity", activity).getResultList();

			BigDecimal estimatedCost = getEstimatedCost(subProtocol);
			return estimatedCost;

		} catch (Exception e) {
			String stringMsg = "Error: geting the cost of the activity sub-protocol  based on its activities";
			logger.log(Level.INFO, stringMsg, e);

			String messageCode = "maintenanceSubProtocol-getSubProtocolCost-fail";
			throw super.generateGHAEJBException(messageCode,
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceLocal#
	 * getSubProtocolDuration(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public int getSubProtocolDuration(Activity activity) throws GHAEJBException {
		try {
			String namedQuery = "SubProtocolAndChecklist.findByParentActivity";
			List<SubProtocolAndChecklist> subProtocol = em
					.createNamedQuery(namedQuery, SubProtocolAndChecklist.class)
					.setParameter("activity", activity).getResultList();

			int estimatedDuration = getEstimatedDurationDays(subProtocol);
			return estimatedDuration;

		} catch (Exception e) {
			String stringMsg = "Error: geting the duration of the activity sub-protocol  based on its activities";
			logger.log(Level.INFO, stringMsg, e);

			String messageCode = "maintenanceSubProtocol-getSubProtocolDuration-fail";
			throw super.generateGHAEJBException(messageCode,
					RuntimeParameters.getLang(), em);
		}
	}

	/**
	 * Get the estimated cost of a sub-protocol based on its activities, not
	 * taking into account the diferences in currency
	 * 
	 * @param protocol
	 *            the list with the maintenance activities of the plan (the
	 *            protocol)
	 * @return the value of the estimated cost of the plan
	 */
	private BigDecimal getEstimatedCost(
			List<SubProtocolAndChecklist> subProtocol) {
		double acum = 0;
		for (SubProtocolAndChecklist entity : subProtocol) {
			Activity activity = entity.getActivity();
			BigDecimal estimatedCost = activity.getEstimatedCost();
			acum += estimatedCost.doubleValue();
		}

		return BigDecimal.valueOf(acum);
	}

	/**
	 * Get the estimated duration of a sub-protocol based on its activities in a
	 * period of days
	 * 
	 * @param subProtocol
	 *            the list with the maintenance activities of the plan (the
	 *            protocol)
	 * @return the ceil value of the estimated duration
	 */
	private int getEstimatedDurationDays(
			List<SubProtocolAndChecklist> subProtocol) {
		final double DAY = 24.0, WEEK = 7.0, MONTH = 30.4368499, SEMESTER = 182.621099, YEAR = 365.242199;

		double totalDays = 0;
		int hours, days, weeks, months, semesters, years;
		hours = days = weeks = months = semesters = years = 0;

		for (SubProtocolAndChecklist entity : subProtocol) {
			Activity activity = entity.getActivity();
			TimePeriodEnum periodOfTime = activity.getEstimatedDurationPoT();
			if (periodOfTime == TimePeriodEnum.HOURS)
				hours += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.DAYS)
				days += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.WEEKS)
				weeks += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.MONTHS)
				months += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.SEMESTERS)
				semesters += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.YEARS)
				years += activity.getEstimatedDuration().intValue();
		}

		totalDays += (hours / DAY) + days + (weeks * WEEK) + (months * MONTH)
				+ (semesters * SEMESTER) + (years * YEAR);

		int totalEstimatedDays = (int) Math.ceil(totalDays);
		return totalEstimatedDays;
	}

	@Override
	public List<SubProtocolAndChecklist> update(
			List<SubProtocolAndChecklist> subProtocols) throws GHAEJBException {
		List<SubProtocolAndChecklist> updatedList = new ArrayList<SubProtocolAndChecklist>();
		try {
			for (SubProtocolAndChecklist subProtocolAndChecklist : subProtocols) {
				em.merge(subProtocolAndChecklist);
				em.flush();
				updatedList.add(em.find(SubProtocolAndChecklist.class,
						subProtocolAndChecklist.getId()));
			}
			return updatedList;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: updating List of SubProtocolAndChecklist ", e);
			throw super.generateGHAEJBException(
					"maintenanceSubProtocol-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
