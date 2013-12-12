/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
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

import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class MaintenancePlanService extends GHAEJBExceptionImpl implements
		MaintenancePlanServiceRemote {
	@PersistenceContext
	EntityManager em;

	@EJB
	EiaPreventiveMaintenancePlanificationServiceLocal preventivePlanifServiceLocal;
	@EJB
	MaintenanceProtocolsServiceRemote protocolsServiceRemote;

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
		if (maintenancePlan.getFrequency() > 0) {
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
					RuntimeParameters.getLang(), em);
		}
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
					RuntimeParameters.getLang(), em);
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
					"maintenancePlan-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
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
					"maintenancePlan-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
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
			if (maintenancePlan.getFrequency() > 0)
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
					"maintenancePlan-findByMaintenancePlan-fail",
					RuntimeParameters.getLang(), em);
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
			throw super.generateGHAEJBException("maintenancePlan-find-fail",
					RuntimeParameters.getLang(), em);
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
					RuntimeParameters.getLang(), em);
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
					RuntimeParameters.getLang(), em);
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
			throw super.generateGHAEJBException("maintenancePlan-save-fail",
					RuntimeParameters.getLang(), em);
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
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#getStadisticInfo
	 * (org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public HashMap<String, Object> getStadisticInfo(
			MaintenancePlan mantenancePlan) throws GHAEJBException {

		try {
			HashMap<String, Object> map = new HashMap<String, Object>();

			Long timesEffectuated = preventivePlanifServiceLocal
					.getEffectuatedPlanificationsCount(mantenancePlan);

			Long numberOfEias = preventivePlanifServiceLocal
					.getPlanificationsCount(mantenancePlan);

			Timestamp lastTimeEffectuated = preventivePlanifServiceLocal
					.getLastEffectuatedPlanificationDate(mantenancePlan);

			List<MaintenanceProtocols> protocols = protocolsServiceRemote
					.findByMaintenancePlan(mantenancePlan);

			map.put("number-activities", protocols.size());
			map.put("estimated-cost", getPlanEstimatedCost(protocols));
			map.put("number-eias", numberOfEias);
			map.put("times-effectuated", timesEffectuated);
			map.put("last-time-effect", lastTimeEffectuated);

			return map;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update MaintenancePlan ",
					e);
			throw super.generateGHAEJBException("maintenancePlan-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	private double getPlanEstimatedCost(List<MaintenanceProtocols> protocols) {
		double acum = 0;
		for (MaintenanceProtocols entity : protocols) {
			MaintenanceActivity activity = entity.getMaintenanceActivity();
			BigDecimal estimatedCost = activity.getEstimatedCost();
			acum += estimatedCost.doubleValue();
		}
		return acum;
	}

	private int getPlanEstimatedDuration(List<MaintenanceProtocols> protocols,
			TimePeriodEnum pot) {
		int acum = 0;
		int acumHOURS, acumDAYS, acumWEEKS, acumMONTHS, acumSEMESTERS, acumYEARS;
		acumHOURS = acumDAYS = acumWEEKS = acumMONTHS = acumSEMESTERS = acumYEARS = 0;

		for (MaintenanceProtocols entity : protocols) {
			MaintenanceActivity activity = entity.getMaintenanceActivity();
			TimePeriodEnum periodOfTime = activity.getEstimatedDurationPoT();
			if (periodOfTime == TimePeriodEnum.HOURS)
				acumHOURS += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.DAYS)
				acumDAYS += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.WEEKS)
				acumWEEKS += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.MONTHS)
				acumMONTHS += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.SEMESTERS)
				acumSEMESTERS += activity.getEstimatedDuration().intValue();
			else if (periodOfTime == TimePeriodEnum.YEARS)
				acumYEARS += activity.getEstimatedDuration().intValue();
		}
		return acum;
	}
}