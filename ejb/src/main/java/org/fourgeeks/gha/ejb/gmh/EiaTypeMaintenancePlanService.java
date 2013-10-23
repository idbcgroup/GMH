/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "gmh.eiaTypeMaintenancePlanService")
public class EiaTypeMaintenancePlanService extends GHAEJBExceptionImpl
		implements EiaTypeMaintenancePlanServiceRemote {

	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeMaintenancePlanService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#delete(
	 * long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			EiaTypeMaintenancePlan entity = em.find(
					EiaTypeMaintenancePlan.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete EiaTypeMaintenancePlan", e);
			throw super.generateGHAEJBException(
					"eiaTypeMaintenancePlan-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#save(org
	 * .fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan save(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan)
			throws GHAEJBException {
		try {
			em.persist(eiaTypeMaintenancePlan);
			em.flush();
			return em.find(EiaTypeMaintenancePlan.class,
					eiaTypeMaintenancePlan.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaTypeMaintenancePlan ", e);
			throw super.generateGHAEJBException(
					"eiaTypeMaintenancePlan-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#findByEiaType
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("EiaTypeMaintenancePlan.findByEiaType",
							EiaTypeMaintenancePlan.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error retriving all EiaTypeMaintenancePlans by eiatype",
					ex);
			throw super.generateGHAEJBException(
					"eiaTypeMaintenancePlan-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#
	 * findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"EiaTypeMaintenancePlan.findByMaintenancePlan",
							EiaTypeMaintenancePlan.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.getResultList();
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error retriving all EiaTypeMaintenancePlans by maintenancePlan",
					ex);
			throw super.generateGHAEJBException(
					"eiaTypeMaintenancePlan-findByMaintenancePlan-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
