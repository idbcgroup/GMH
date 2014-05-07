package org.fourgeeks.gha.ejb.gmh;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fourgeeks.gha.domain.enu.EiaMaintenanceState;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * Session Bean implementation class
 * EiaPreventiveMaintenancePlanificationService
 */
@Stateless
public class EiaMaintenancePlanificationService extends GHAEJBExceptionService
		implements EiaMaintenancePlanificationServiceRemote,
		EiaMaintenancePlanificationServiceLocal {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaMaintenancePlanificationService.class.getName());

	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			EiaMaintenancePlanification entity = em.find(
					EiaMaintenancePlanification.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete EiaMaintenancePlanification", e);
			throw super.generateGHAEJBException(
					"EiaMaintenancePlanification-delete-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote#find
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException {
		try {
			List<EiaMaintenancePlanification> resultList = em
					.createNamedQuery(
							"EiaMaintenancePlanification.findByEiaType",
							EiaMaintenancePlanification.class)
					.setParameter("eiaType", eiaType).getResultList();

			return resultList;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding EiaMaintenancePlanification by eiatype", e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail", em);
		}
	}

	@Override
	public List<EiaMaintenancePlanification> getAll() throws GHAEJBException {
		try {
			List<EiaMaintenancePlanification> resultList = em.createNamedQuery(
					"EiaMaintenancePlanification.getAll",
					EiaMaintenancePlanification.class).getResultList();

			return resultList;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding all EiaMaintenancePlanification", e);
			throw super.generateGHAEJBException(
					"eiaMaintenancePlanification-getAll-fail", em);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long getEffectuatedPlanificationsCount(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			String stringQuery = "SELECT COUNT(epm) FROM EiaPreventiveMaintenance epm "
					+ "JOIN epm.planification planif "
					+ "JOIN planif.plan plan "
					+ "WHERE plan.maintenancePlan = :maintenancePlan AND epm.state = :state";

			Long result = em.createQuery(stringQuery, Long.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.setParameter("state", EiaMaintenanceState.ACCOMPLISHED)
					.getSingleResult();

			return result;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: geting the number of effectuated preventive maintenance for the given maintenancePlan",
					e);
			throw super
					.generateGHAEJBException(
							"eiaPreventiveMaintenance-getEffectuatedPlanificationsCount-fail",
							em);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Timestamp getLastEffectuatedPlanificationDate(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			String stringQuery = "SELECT epm FROM EiaPreventiveMaintenance epm "
					+ "JOIN epm.planification planif "
					+ "JOIN planif.plan plan "
					+ "WHERE plan.maintenancePlan = :maintenancePlan AND epm.state = :status AND epm.finishTimestamp IS NOT NULL "
					+ "ORDER BY epm.finishTimestamp desc ";

			List<EiaPreventiveMaintenance> resultList = em
					.createQuery(stringQuery, EiaPreventiveMaintenance.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.setParameter("status", EiaMaintenanceState.ACCOMPLISHED)
					.getResultList();

			if (resultList.isEmpty())
				return null;

			EiaPreventiveMaintenance maintenance = resultList.get(0);
			Timestamp finishTimestamp = maintenance.getFinishTimestamp();

			return finishTimestamp;

		} catch (Exception e) {
			String msg = "Error: getting the last date of an effectuated preventive maintenance for the given maintenancePlan";
			logger.log(Level.INFO, msg, e);
			String messageCode = "eiaMaintenancePlanification-getLastEffectuatedPlanificationDate-fail";
			throw super.generateGHAEJBException(messageCode, em);
		}
	}

	@Override
	public Date getScheduleDateOfLastMaintenance(
			EiaMaintenancePlanification planif) throws GHAEJBException {
		try {
			String stringQuery = "SELECT epm FROM EiaPreventiveMaintenance epm JOIN epm.planification planif "
					+ "WHERE planif = :planif ORDER BY epm.scheduledDate desc ";

			List<EiaPreventiveMaintenance> resultList = em
					.createQuery(stringQuery, EiaPreventiveMaintenance.class)
					.setParameter("planif", planif).getResultList();

			if (resultList.isEmpty())
				return null;

			EiaPreventiveMaintenance maintenance = resultList.get(0);
			Date finishTimestamp = maintenance.getScheduledDate();

			return finishTimestamp;

		} catch (Exception e) {
			String msg = "Error: geting the schedule date of the last maintenance for the given EiaMaintenancePlanification";
			logger.log(Level.INFO, msg, e);
			String messageCode = "eiaMaintenancePlanification-getScheduleDateOfLastMaintenance-fail";
			throw super.generateGHAEJBException(messageCode, em);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Long getPlanificationsCount(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		try {
			String stringQuery = "SELECT COUNT(epm) FROM EiaMaintenancePlanification epm "
					+ "JOIN epm.plan plan "
					+ "WHERE plan.maintenancePlan = :maintenancePlan ";

			Long result = em.createQuery(stringQuery, Long.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.getSingleResult();

			return result;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: geting the number of maintenance planifications associated to the given maintenancePlan",
					e);
			throw super.generateGHAEJBException(
					"eiaPreventiveMaintenance-getPlanificationsCount-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote#save
	 * (org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification)
	 */
	@Override
	public EiaMaintenancePlanification save(EiaMaintenancePlanification planif)
			throws GHAEJBException {
		try {
			em.persist(planif);
			em.flush();

			return em.find(EiaMaintenancePlanification.class, planif.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving EiaMaintenancePlanification ", e);
			throw super.generateGHAEJBException("eia-save-fail", em);

		}

	}

	@Override
	public boolean existMantenancePlanification(Eia eia,
			EiaTypeMaintenancePlan plan) throws GHAEJBException {
		try {

			String stringQuery = "SELECT emp FROM EiaMaintenancePlanification emp  "
					+ "WHERE emp.eia = :name_eia and emp.plan = :name_plan ";

			List<EiaMaintenancePlanification> resultList = em
					.createQuery(stringQuery, EiaMaintenancePlanification.class)
					.setParameter("name_eia", eia)
					.setParameter("name_plan", plan).getResultList();

			if (resultList.isEmpty())
				return false;

			return true;

		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: finding in the method existMantenancePlanification by eia and EiaTypeMaintenancePlan ",
					e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail", em);
		}
	}

	@Override
	public List<EiaPlanificationEntity> findEiaMaintenancePlanificationStatus(
			Eia eia, EiaTypeMaintenancePlan plan) throws GHAEJBException {
		try {
			String queryStr = "SELECT eia FROM Eia eia ";

			// String queryStr =
			// "SELECT new EiaPlanificationEntity(eia, p) FROM Eia eia "
			// + "LEFT JOIN eia.eMaintenancePlan p ";
			//
			String whereStr = "WHERE eia.eiaType =:eiaType";

			whereStr = buildWhere(whereStr, eia.getFixedAssetIdentifier(),
					"eia.fixedAssetIdentifier", "LIKE", ":fixedAssetIdentifier");

			whereStr = buildWhere(whereStr, eia.getSerialNumber(),
					"eia.serialNumber", "LIKE", ":serialNumber");

			whereStr = buildWhere(whereStr, eia.getState(), "eia.state", "=",
					":state");

			whereStr = buildWhere(whereStr, eia.getWorkingArea(),
					"eia.workingArea", "=", ":workingArea");

			queryStr += whereStr;

			// creo el query y le asigno los parametros
			TypedQuery<Eia> query = em.createQuery(queryStr, Eia.class);
			if (eia.getFixedAssetIdentifier() != null)
				query.setParameter("fixedAssetIdentifier",
						"%" + eia.getFixedAssetIdentifier() + "%");

			if (eia.getSerialNumber() != null)
				query.setParameter("serialNumber", "%" + eia.getSerialNumber()
						+ "%");

			if (eia.getWorkingArea() != null)
				query.setParameter("workingArea", eia.getWorkingArea());

			query.setParameter("state", eia.getState());
			query.setParameter("eiaType", plan.getEiaType());

			// devuelvo la lista de EiaPlanificationEntity
			List<Eia> tempList = query.getResultList();

			String queryStr2 = "SELECT emp FROM EiaMaintenancePlanification emp ";
			String whereStr2 = "WHERE emp.plan =:plan";

			queryStr2 += whereStr2;

			TypedQuery<EiaMaintenancePlanification> query2 = em.createQuery(
					queryStr2, EiaMaintenancePlanification.class);

			query2.setParameter("plan", plan);

			List<EiaMaintenancePlanification> tempList2 = query2
					.getResultList();

			System.out.println("tempList.size(): " + tempList.size()
					+ " tempList2.size(): " + tempList2.size());

			boolean hasPlan = false;
			List<EiaPlanificationEntity> resultList = new ArrayList<EiaPlanificationEntity>();
			for (Eia eiaTemp : tempList) {
				hasPlan = false;
				for (EiaMaintenancePlanification empTemp : tempList2) {
					if (empTemp.getEia().equals(eiaTemp)) {
						resultList.add(new EiaPlanificationEntity(eiaTemp,
								empTemp));
						hasPlan = true;
						break;
					}
				}
				if (!hasPlan)
					resultList.add(new EiaPlanificationEntity(eiaTemp, null));
			}
			return resultList;

		} catch (NoResultException ex) {
			logger.log(Level.INFO, "No results", ex);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error en metodo EJB findEiasByEiaTypes", ex);
			throw new GHAEJBException(
					"Error en metodo EJB findEiasByEiaTypes: "
							+ ex.getCause().getMessage());
		}

		return null;
	}

	/**
	 * @param query
	 *            El query
	 * @param elem
	 *            El elemento que se desea asignar a la clausula where
	 * @param campo
	 *            el camp que representa el emento dentro de la consulta
	 * @param op
	 *            la operacion comparacion
	 * @param param
	 *            el parametro contra el que se va a realziar la operacion de
	 *            comparacion
	 * @return String de la consulta con el parametro deseado en la clausula
	 *         where
	 */
	private String buildWhere(String query, Object elem, String campo,
			String op, String param) {

		if (elem != null) {
			query += query.isEmpty() ? " WHERE " : " AND ";
			query += campo + " " + op + " " + param;
		}
		return query;
	}

	@Override
	public List<EiaMaintenancePlanification> save(
			List<EiaMaintenancePlanification> listPlanif)
			throws GHAEJBException {
		try {
			List<EiaMaintenancePlanification> returnList = new ArrayList<EiaMaintenancePlanification>();

			for (EiaMaintenancePlanification planif : listPlanif) {

				if (planif.getId() == 0) {
					em.persist(planif);
					em.flush();
				} else
					em.merge(planif);
				returnList.add(em.find(EiaMaintenancePlanification.class,
						planif.getId()));
			}
			return returnList;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving EiaMaintenancePlanification ", e);
			throw super.generateGHAEJBException("eia-save-fail", em);

		}
	}
}