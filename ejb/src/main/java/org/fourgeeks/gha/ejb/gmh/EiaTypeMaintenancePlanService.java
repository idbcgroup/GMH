/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.eiaTypeMaintenancePlanService")
public class EiaTypeMaintenancePlanService implements
		EiaTypeMaintenancePlanServiceRemote {
	
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaTypeMaintenancePlanService.class
			.getName());

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			EiaTypeMaintenancePlan entity = em.find(EiaTypeMaintenancePlan.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete EiaTypeMaintenancePlan", e);
			throw new GHAEJBException("Error eliminando EiaTypeMaintenancePlan por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenancePlanServiceRemote#save(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan save(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan)
			throws GHAEJBException {
		try {
			em.persist(eiaTypeMaintenancePlan);
			em.flush();
			return em.find(EiaTypeMaintenancePlan.class, eiaTypeMaintenancePlan.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaTypeMaintenancePlan ", e);
			throw new GHAEJBException("ERROR: saving EiaTypeMaintenancePlan "
					+ e.getCause().getMessage());
		}
	}

}
