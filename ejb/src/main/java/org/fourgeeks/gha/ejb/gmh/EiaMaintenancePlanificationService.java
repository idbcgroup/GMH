package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * Session Bean implementation class EiaMaintenancePlanification
 */
@Stateless
public class EiaMaintenancePlanificationService extends GHAEJBExceptionService
		implements EiaMaintenancePlanificationServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaMaintenancePlanificationService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationRemote#find(org.
	 * fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaMaintenancePlanification> find(EiaType eiaType)
			throws GHAEJBException {
		try {
			List<EiaMaintenancePlanification> resultList = em
					.createNamedQuery("findByEiaType",
							EiaMaintenancePlanification.class)
					.setParameter("eiaType", eiaType).getResultList();

			return resultList;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding EiaMaintenancePlanification by eiatype", e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationRemote#
	 * getCorrectiveMaintenancePlanification
	 * (org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanification)
	 */
	@Override
	public EiaCorrectiveMaintenancePlanification getCorrectiveMaintenancePlanification(
			EiaMaintenancePlanification entity) throws GHAEJBException {
		try {
			String stringQuery = "SELECT cmp FROM EiaCorrectiveMaintenancePlanification cmp WHERE cmp.planification = :planification";
			EiaCorrectiveMaintenancePlanification result = em
					.createQuery(stringQuery,
							EiaCorrectiveMaintenancePlanification.class)
					.setParameter("planification", entity).getSingleResult();

			return result;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: finding EiaCorrectiveMaintenancePlanification by EiaMaintenancePlanification",
					e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationRemote#
	 * getPreventiveMaintenancePlanification
	 * (org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanification)
	 */
	@Override
	public EiaPreventiveMaintenancePlanification getPreventiveMaintenancePlanification(
			EiaMaintenancePlanification entity) throws GHAEJBException {
		try {
			String stringQuery = "SELECT pmp FROM EiaPreventiveMaintenancePlanification pmp WHERE pmp.planification = :planification";
			EiaPreventiveMaintenancePlanification result = em
					.createQuery(stringQuery,
							EiaPreventiveMaintenancePlanification.class)
					.setParameter("planification", entity).getSingleResult();

			return result;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: finding EiaPreventiveMaintenancePlanification by EiaMaintenancePlanification",
					e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationRemote#
	 * saveCorrectiveMaintenance
	 * (org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenancePlanification)
	 */
	@Override
	public EiaCorrectiveMaintenancePlanification saveCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity)
			throws GHAEJBException {
		try {
			em.persist(entity.getPlanification());
			em.persist(entity);
			// TODO agregar en tabla log: se creo un mantenimiento correctivo
			em.flush();

			return em.find(EiaCorrectiveMaintenancePlanification.class,
					entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving EiaCorrectiveMaintenancePlanification ", e);
			throw super.generateGHAEJBException("eia-save-fail",
					RuntimeParameters.getLang(), em);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationRemote#
	 * savePreventiveMaintenance
	 * (org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification)
	 */
	@Override
	public EiaPreventiveMaintenancePlanification savePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity)
			throws GHAEJBException {

		EiaMaintenancePlanification planif = entity.getPlanification();
		try {
			em.persist(planif);
			em.persist(entity);
			// TODO agregar en tabla log: se creo un mantenimiento preventivo
			em.flush();

			return em.find(EiaPreventiveMaintenancePlanification.class,
					entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving EiaPreventiveMaintenancePlanification ", e);
			throw super.generateGHAEJBException("eia-save-fail",
					RuntimeParameters.getLang(), em);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote#update
	 * (org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification)
	 */
	@Override
	public EiaPreventiveMaintenancePlanification updatePreventiveMaintenance(
			EiaPreventiveMaintenancePlanification entity)
			throws GHAEJBException {

		try {
			EiaMaintenancePlanification planif = entity.getPlanification();
			EiaStateEnum finalEiaState = planif.getFinalEiaState();

			if (finalEiaState != null) {
				Eia eia = planif.getEia();
				if (!eia.getState().equals(finalEiaState)) {
					eia.setState(finalEiaState);
					em.merge(eia);
					// TODO agregar a tabla log: cambio del estado del eia
					// generado por el mantenimiento
				}
			}

			em.merge(planif);
			EiaPreventiveMaintenancePlanification res = em.merge(entity);
			em.flush();

			return res;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"ERROR: unable to update EiaPreventiveMaintenancePlanification ",
					e);
			throw super.generateGHAEJBException("eia-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationServiceRemote#update
	 * (org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification)
	 */
	@Override
	public EiaCorrectiveMaintenancePlanification updateCorrectiveMaintenance(
			EiaCorrectiveMaintenancePlanification entity)
			throws GHAEJBException {

		try {
			EiaMaintenancePlanification planif = entity.getPlanification();
			EiaStateEnum finalEiaState = planif.getFinalEiaState();

			if (finalEiaState != null) {
				Eia eia = planif.getEia();
				if (!eia.getState().equals(finalEiaState)) {
					eia.setState(finalEiaState);
					em.merge(eia);
					// TODO agregar a tabla log: cambio del estado del eia
					// generado por el mantenimiento
				}
			}

			em.merge(planif);
			EiaCorrectiveMaintenancePlanification res = em.merge(entity);
			em.flush();

			return res;
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"ERROR: unable to update EiaCorrectiveMaintenancePlanification ",
					e);
			throw super.generateGHAEJBException("eia-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}