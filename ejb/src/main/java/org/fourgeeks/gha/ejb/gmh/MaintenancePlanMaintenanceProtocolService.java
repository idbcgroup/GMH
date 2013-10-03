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
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.maintenancePlanMaintenanceProtocolService")
public class MaintenancePlanMaintenanceProtocolService implements
		MaintenancePlanMaintenanceProtocolServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(MaintenancePlanMaintenanceProtocolService.class
			.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			MaintenancePlanMaintenanceProtocol entity = em.find(MaintenancePlanMaintenanceProtocol.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete MaintenancePlanMaintenanceProtocol", e);
			throw new GHAEJBException("Error eliminando MaintenancePlanMaintenanceProtocol por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote#save(org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol)
	 */
	@Override
	public MaintenancePlanMaintenanceProtocol save(
			MaintenancePlanMaintenanceProtocol maintenancePlanMaintenanceProtocol)
			throws GHAEJBException {
		try {
			em.persist(maintenancePlanMaintenanceProtocol);
			em.flush();
			return em.find(MaintenancePlanMaintenanceProtocol.class, maintenancePlanMaintenanceProtocol.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenancePlanMaintenanceProtocol ", e);
			throw new GHAEJBException("ERROR: saving MaintenancePlanMaintenanceProtocol "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote#findByMaintenanceProtocol(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public List<MaintenancePlanMaintenanceProtocol> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenancePlanMaintenanceProtocol.findByMaintenanceProtocol", MaintenancePlanMaintenanceProtocol.class)
					.setParameter("maintenanceProtocol", maintenanceProtocol).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all MaintenancePlanMaintenanceProtocol by MaintenanceProtocol", ex);
			throw new GHAEJBException(
					"Error obteniendo todos los MaintenancePlanMaintenanceProtocol por protocolo de mantenimiento");
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanMaintenanceProtocolServiceRemote#findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenancePlanMaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenancePlanMaintenanceProtocol.findByMaintenancePlan", MaintenancePlanMaintenanceProtocol.class)
					.setParameter("maintenancePlan", maintenancePlan).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all MaintenancePlanMaintenanceProtocol by maintenancePlan", ex);
			throw new GHAEJBException(
					"Error obteniendo todos los MaintenancePlanMaintenanceProtocol por plan de mantenimiento");
		}
	}


}
