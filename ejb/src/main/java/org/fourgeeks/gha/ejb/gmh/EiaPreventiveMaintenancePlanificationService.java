package org.fourgeeks.gha.ejb.gmh;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationStatus;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class
 * EiaPreventiveMaintenancePlanificationService
 */
@Stateless
public class EiaPreventiveMaintenancePlanificationService extends
		GHAEJBExceptionImpl implements
		EiaPreventiveMaintenancePlanificationServiceRemote,
		EiaPreventiveMaintenancePlanificationServiceLocal {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaPreventiveMaintenancePlanificationService.class
					.getName());

	@Override
	public List<EiaPreventiveMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException {
		try {
			ArrayList<EiaStateEnum> stateList = new ArrayList<EiaStateEnum>();
			stateList.add(EiaStateEnum.DAMAGED);
			stateList.add(EiaStateEnum.MAINTENANCE);

			String stringQuery = "SELECT pmp FROM EiaPreventiveMaintenancePlanification pmp JOIN pmp.plan plan WHERE plan.eiaType = :eiaType order by pmp.id";
			List<EiaPreventiveMaintenancePlanification> resultList = em
					.createQuery(stringQuery,
							EiaPreventiveMaintenancePlanification.class)
					.setParameter("eiaType", eiaType).getResultList();

			return resultList;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: finding EiaPreventiveMaintenancePlanification by eiatype",
					e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public EiaPreventiveMaintenancePlanification save(
			EiaPreventiveMaintenancePlanification preventivePlanif)
			throws GHAEJBException {
		try {
			em.persist(preventivePlanif.getPlanification());
			em.persist(preventivePlanif);
			// TODO agregar en tabla log: se creo un mantenimiento preventivo
			em.flush();

			return em.find(EiaPreventiveMaintenancePlanification.class,
					preventivePlanif.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving EiaPreventiveMaintenancePlanification ", e);
			throw super.generateGHAEJBException("eia-save-fail",
					RuntimeParameters.getLang(), em);

		}

	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long getEffectuatedPlanificationsCount(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			String stringQuery = "SELECT COUNT(pmp) FROM EiaPreventiveMaintenancePlanification pmp "
					+ "JOIN pmp.plan plan "
					+ "JOIN pmp.planification planif "
					+ "WHERE plan.maintenancePlan = :maintenancePlan AND planif.status = :status";

			Long result = em
					.createQuery(stringQuery, Long.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.setParameter("status",
							MaintenancePlanificationStatus.ACCOMPLISHED)
					.getSingleResult();

			return result;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: geting the number of effectuated preventive planifications for the given maintenancePlan",
					e);
			throw super
					.generateGHAEJBException(
							"eiaPreventiveMaintenance-getEffectuatedPlanificationsCount-fail",
							RuntimeParameters.getLang(), em);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long getPlanificationsCount(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		try {
			String stringQuery = "SELECT COUNT(pmp) FROM EiaPreventiveMaintenancePlanification pmp "
					+ "JOIN pmp.plan plan "
					+ "WHERE plan.maintenancePlan = :maintenancePlan";

			Long result = em.createQuery(stringQuery, Long.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.getSingleResult();

			return result;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: geting the number of preventive planifications associated to the given maintenancePlan",
					e);
			throw super.generateGHAEJBException(
					"eiaPreventiveMaintenance-getPlanificationsCount-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Timestamp getLastEffectuatedPlanificationDate(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			String stringQuery = "SELECT planif FROM EiaPreventiveMaintenancePlanification pmp "
					+ "JOIN pmp.plan plan "
					+ "JOIN pmp.planification planif "
					+ "WHERE plan.maintenancePlan = :maintenancePlan AND planif.status = :status AND planif.finishTimestamp IS NOT NULL "
					+ "ORDER BY planif.finishTimestamp asc";

			List<EiaMaintenancePlanification> resultList = em
					.createQuery(stringQuery, EiaMaintenancePlanification.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.setParameter("status",
							MaintenancePlanificationStatus.ACCOMPLISHED)
					.getResultList();

			if (resultList.isEmpty())
				return null;

			EiaMaintenancePlanification planification = resultList.get(0);
			Timestamp finishTimestamp = planification.getFinishTimestamp();

			return finishTimestamp;

		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: geting the last date of an effectuated preventive planification for the given maintenancePlan",
					e);
			throw super
					.generateGHAEJBException(
							"eiaPreventiveMaintenance-getEffectuatedPlanificationsCount-fail",
							RuntimeParameters.getLang(), em);
		}
	}
}
