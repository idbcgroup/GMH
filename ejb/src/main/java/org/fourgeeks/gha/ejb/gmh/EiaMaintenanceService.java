package org.fourgeeks.gha.ejb.gmh;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * Session Bean implementation class EiaMaintenancePlanification
 */
@Stateless
public class EiaMaintenanceService extends GHAEJBExceptionService implements
		EiaMaintenanceServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaMaintenanceService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaMaintenancePlanificationRemote#find(org.
	 * fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaMaintenance> find(EiaType eiaType) throws GHAEJBException {
		try {
			ArrayList<EiaMaintenance> resultListTotal = new ArrayList<EiaMaintenance>();

			List<EiaPreventiveMaintenance> resultListPreventive = em
					.createNamedQuery("EiaPreventiveMaintenance.findByEiaType",
							EiaPreventiveMaintenance.class)
					.setParameter("eiaType", eiaType).getResultList();

			List<EiaCorrectiveMaintenance> resultListCorrective = em
					.createNamedQuery("EiaCorrectiveMaintenance.findByEiaType",
							EiaCorrectiveMaintenance.class)
					.setParameter("eiaType", eiaType).getResultList();

			resultListTotal.addAll(resultListPreventive);
			resultListTotal.addAll(resultListCorrective);

			return resultListTotal;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding EiaMaintenancePlanification by eiatype", e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail", em);
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
	public EiaCorrectiveMaintenance saveCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();

			return em.find(EiaCorrectiveMaintenance.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaCorrectiveMaintenance ", e);
			throw super.generateGHAEJBException(
					"EiaCorrectiveMaintenance-save-fail", em);

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
	public EiaPreventiveMaintenance savePreventiveMaintenance(
			EiaPreventiveMaintenance entity) throws GHAEJBException {

		try {
			em.persist(entity);
			em.flush();

			return em.find(EiaPreventiveMaintenance.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaPreventiveMaintenance ", e);
			throw super.generateGHAEJBException(
					"EiaPreventiveMaintenance-save-fail", em);

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
	public EiaPreventiveMaintenance updatePreventiveMaintenance(
			EiaPreventiveMaintenance entity) throws GHAEJBException {

		try {
			EiaPreventiveMaintenance res = em.merge(entity);
			em.flush();

			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update EiaPreventiveMaintenance ", e);
			throw super.generateGHAEJBException("eia-update-fail", em);
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
	public EiaCorrectiveMaintenance updateCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity) throws GHAEJBException {

		try {
			EiaCorrectiveMaintenance res = em.merge(entity);
			em.flush();

			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update EiaCorrectiveMaintenance ", e);
			throw super.generateGHAEJBException("eia-update-fail", em);
		}
	}

	@Override
	public void deleteCorrectiveMaintenance(long Id) throws GHAEJBException {
		try {
			EiaCorrectiveMaintenance entity = em.find(
					EiaCorrectiveMaintenance.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete EiaCorrectiveMaintenance", e);
			throw super.generateGHAEJBException(
					"EiaCorrectiveMaintenance-delete-fail", em);
		}
	}

	@Override
	public void deletePreventiveMaintenance(long Id) throws GHAEJBException {
		try {
			EiaPreventiveMaintenance entity = em.find(
					EiaPreventiveMaintenance.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete EiaPreventiveMaintenance", e);
			throw super.generateGHAEJBException(
					"EiaPreventiveMaintenance-delete-fail", em);
		}
	}
}
