/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.enu.EiaMaintenanceState;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanStadisticData;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author emiliot, vivi.torresg, naramirez
 * 
 */

@Stateless
public class MaintenancePlanService extends GHAEJBExceptionService implements
		MaintenancePlanServiceRemote {
	@PersistenceContext
	EntityManager em;

	@EJB
	EiaMaintenancePlanificationServiceLocal preventivePlanifServiceLocal;
	@EJB
	MaintenanceProtocolServiceRemote protocolsServiceRemote;

	private final static Logger logger = Logger
			.getLogger(MaintenancePlanService.class.getName());

	/**
	 * @param maintenancePlan
	 * @param cb
	 * @param root
	 * @return
	 */
	private static Predicate buildFilters(MaintenancePlan maintenancePlan,
			CriteriaBuilder cb, Root<MaintenancePlan> root) {
		Predicate predicate = cb.conjunction();
		if (maintenancePlan.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("name")), p));
		}
		if (maintenancePlan.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("description")), p));
		}
		if (maintenancePlan.getPot() != null) {
			ParameterExpression<TimePeriodEnum> p = cb.parameter(
					TimePeriodEnum.class, "pot");
			predicate = cb.and(predicate,
					cb.equal(root.<TimePeriodEnum> get("pot"), p));
		}
		if (maintenancePlan.getFrequency() != null) {
			ParameterExpression<Integer> p = cb.parameter(Integer.class,
					"frequency");
			predicate = cb.and(predicate,
					cb.equal(root.<Integer> get("frequency"), p));
		}

		if (maintenancePlan.getState() != null) {
			ParameterExpression<MaintenancePlanState> p = cb.parameter(
					MaintenancePlanState.class, "state");
			predicate = cb.and(predicate,
					cb.equal(root.<MaintenancePlanState> get("state"), p));
		}

		if (maintenancePlan.getType() != null) {
			ParameterExpression<MaintenancePlanType> p = cb.parameter(
					MaintenancePlanType.class, "type");
			predicate = cb.and(predicate,
					cb.equal(root.<MaintenancePlanType> get("type"), p));
		}

		return predicate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#delete(java.util
	 * .List)
	 */
	@Override
	public void delete(List<MaintenancePlan> maintenancePlans)
			throws GHAEJBException {
		try {
			for (MaintenancePlan maintenancePlan : maintenancePlans) {
				MaintenancePlan entity = em.find(MaintenancePlan.class,
						maintenancePlan.getId());
				em.remove(entity);
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete MaintenancePlan", e);
			throw super.generateGHAEJBException("maintenancePlan-delete-fail",
					em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#findEiaByEiaType
	 * (org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<EiaMaintenancePlanification> findEiaByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenancePlan.findEiaByMaintenancePlan",
							EiaMaintenancePlanification.class)
					.setParameter("plan", maintenancePlan).getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error: finding Eia by MaintenancePlan", e);
			throw super.generateGHAEJBException(
					"maintenancePlan-findEiaByMaintenancePlan-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#
	 * findEiaByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<Eia> findDamageEiaByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			ArrayList<EiaStateEnum> states = new ArrayList<EiaStateEnum>();
			states.add(EiaStateEnum.DAMAGED);
			states.add(EiaStateEnum.MAINTENANCE);
			states.add(EiaStateEnum.MAYOR_DAMAGED);
			return em
					.createNamedQuery(
							"MaintenancePlan.findDamageEiaByMaintenancePlan",
							Eia.class).setParameter("eiastates", states)
					.setParameter("mstate", EiaMaintenanceState.DEFERRED)
					.setParameter("plan", maintenancePlan).getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error: finding damaged Eias by MaintenancePlan", e);
			throw super.generateGHAEJBException(
					"maintenancePlan-findDamagedEiaByMaintenancePlan-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			MaintenancePlan entity = em.find(MaintenancePlan.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete MaintenancePlan", e);
			throw super.generateGHAEJBException("maintenancePlan-delete-fail",
					em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(long)
	 */
	@Override
	public MaintenancePlan find(long Id) throws GHAEJBException {
		try {
			return em.find(MaintenancePlan.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaintenancePlan", e);
			throw super
					.generateGHAEJBException("maintenancePlan-find-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(org.fourgeeks
	 * .gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenancePlan> find(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MaintenancePlan> cQuery = cb
					.createQuery(MaintenancePlan.class);
			Root<MaintenancePlan> root = cQuery.from(MaintenancePlan.class);

			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("name")));
			Predicate criteria = buildFilters(maintenancePlan, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<MaintenancePlan> q = em.createQuery(cQuery);

			if (maintenancePlan.getName() != null)
				q.setParameter("name", "%"
						+ maintenancePlan.getName().toLowerCase() + "%");
			if (maintenancePlan.getDescription() != null)
				q.setParameter("description", "%"
						+ maintenancePlan.getDescription().toLowerCase() + "%");
			if (maintenancePlan.getPot() != null)
				q.setParameter("pot", maintenancePlan.getPot());
			if (maintenancePlan.getFrequency() != null)
				q.setParameter("frequency", maintenancePlan.getFrequency());
			if (maintenancePlan.getState() != null)
				q.setParameter("state", maintenancePlan.getState());
			if (maintenancePlan.getType() != null)
				q.setParameter("type", maintenancePlan.getType());

			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los maintenancePlan por maintenancePlan",
					e);
			throw super.generateGHAEJBException(
					"maintenancePlan-findByMaintenancePlan-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public List<MaintenancePlan> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaintenancePlan.findByEiaType",
							MaintenancePlan.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenancePlan", e);
			throw super.generateGHAEJBException(
					"maintenancePlan-findByEiaType-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(org.fourgeeks
	 * .gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<MaintenancePlan> findByEiaType(EiaType eiaType, int offset,
			int size) throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaintenancePlan.findByEiaType",
							MaintenancePlan.class)
					.setParameter("eiaType", eiaType).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenancePlan", e);
			throw super.generateGHAEJBException(
					"maintenancePlan-findByEiaType-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#getAll()
	 */
	@Override
	public List<MaintenancePlan> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenancePlan.getAll",
					MaintenancePlan.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenancePlan", e);
			throw super.generateGHAEJBException("maintenancePlan-getAll-fail",
					em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<MaintenancePlan> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaintenancePlan.getAll",
							MaintenancePlan.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenancePlan", e);
			throw super.generateGHAEJBException("maintenancePlan-getAll-fail",
					em);
		}
	}

	/**
	 * Get the estimated cost of a plan based on its activities, not taking into
	 * account the diferences in currency
	 * 
	 * @param protocol
	 *            the list with the maintenance activities of the plan (the
	 *            protocol)
	 * @return the value of the estimated cost of the plan
	 */
	private BigDecimal getPlanEstimatedCost(List<MaintenanceProtocol> protocol) {
		double acum = 0;
		return BigDecimal.valueOf(acum);
	}

	/**
	 * Get the estimated duration of a plan based on its activities in a period
	 * of days
	 * 
	 * @param protocol
	 *            the list with the maintenance activities of the plan (the
	 *            protocol)
	 * @return the ceil value of the estimated duration
	 */
	private int getPlanEstimatedDurationDays(List<MaintenanceProtocol> protocol) {
		final double DAY = 24.0, WEEK = 7.0, MONTH = 30.4368499, SEMESTER = 182.621099, YEAR = 365.242199;

		double totalDays = 0;
		int hours, days, weeks, months, semesters, years;
		hours = days = weeks = months = semesters = years = 0;

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#getStadisticInfo
	 * (org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenancePlanStadisticData getStadisticInfo(
			MaintenancePlan maintenancePlan) throws GHAEJBException {

		try {
			MaintenancePlanStadisticData data = new MaintenancePlanStadisticData();

			long timesEffectuated = preventivePlanifServiceLocal
					.getEffectuatedPlanificationsCount(maintenancePlan);

			long numberOfEias = preventivePlanifServiceLocal
					.getPlanificationsCount(maintenancePlan);

			Timestamp lastTimeEffectuated = preventivePlanifServiceLocal
					.getLastEffectuatedPlanificationDate(maintenancePlan);

			List<MaintenanceProtocol> protocol = protocolsServiceRemote
					.findByMaintenancePlan(maintenancePlan);

			data.setNumberActivities(protocol.size());
			data.setEstimatedCost(getPlanEstimatedCost(protocol));
			data.setEstimatedDuration(getPlanEstimatedDurationDays(protocol));
			data.setNumberOfEias(numberOfEias);
			data.setTimesEffectuated(timesEffectuated);
			data.setLastTimeEffectuated(lastTimeEffectuated);

			return data;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"ERROR: unable to get stadistic info from the MaintenancePlan ",
					e);
			throw super.generateGHAEJBException("maintenancePlan-update-fail",
					em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenancePlan save(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		try {
			em.persist(maintenancePlan);
			em.flush();
			return em.find(MaintenancePlan.class, maintenancePlan.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenancePlan ", e);
			throw super
					.generateGHAEJBException("maintenancePlan-save-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#update(org.fourgeeks
	 * .gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenancePlan update(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		try {
			MaintenancePlan res = em.merge(maintenancePlan);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update MaintenancePlan ",
					e);
			throw super.generateGHAEJBException("maintenancePlan-update-fail",
					em);
		}
	}
}