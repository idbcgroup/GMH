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

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.EiaTypeMaintenancePlanService")
public class EiaTypeMaintenancePlanService implements
		EiaTypeMaintenancePlanServiceRemote {

	@PersistenceContext
	private EntityManager em;

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
	public void delete(long Id) throws EJBException {
		try {
			EiaTypeMaintenancePlan entity = em.find(
					EiaTypeMaintenancePlan.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete EiaTypeMaintenancePlan", e);
			throw new EJBException(
					"ERROR: unable to delete EiaTypeMaintenancePlan "
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#find(org
	 * .fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByEiaType(long eiaTypeId)
			throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenancePlan.findByEiaType",
					EiaTypeMaintenancePlan.class).setParameter("eiaType", eiaTypeId).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving EiaTypeMaintenancePlan by EiaType", ex);
			throw new EJBException("Error obteniendo todos los EiaTypeMaintenancePlan"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#find(long)
	 */
	@Override
	public EiaTypeMaintenancePlan find(long Id) throws EJBException {
		try {
			return em.find(EiaTypeMaintenancePlan.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding EiaTypeMaintenancePlan", e);
			throw new EJBException("ERROR: finding EiaTypeMaintenancePlan "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#getAll()
	 */
	@Override
	public List<EiaTypeMaintenancePlan> getAll() throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenancePlan.getAll",
					EiaTypeMaintenancePlan.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeMaintenancePlan", ex);
			throw new EJBException("Error obteniendo todas las EiaTypeMaintenancePlan"
					+ ex.getCause().getMessage());
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
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		try {
			em.persist(eiaTypeMaintenancePlan);
			em.flush();
			return em.find(EiaTypeMaintenancePlan.class,
					eiaTypeMaintenancePlan.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaTypeMaintenancePlan "
					+ eiaTypeMaintenancePlan.toString(), e);
			throw new EJBException("ERROR: saving EiaTypeMaintenancePlan "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#update(
	 * org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan update(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		try {
			EiaTypeMaintenancePlan res = em.merge(eiaTypeMaintenancePlan);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update brand "
					+ eiaTypeMaintenancePlan.toString(), e);
			throw new EJBException("ERROR: no se puede actualizar el brand "
					+ e.getCause().getMessage());
		}
	}

}
