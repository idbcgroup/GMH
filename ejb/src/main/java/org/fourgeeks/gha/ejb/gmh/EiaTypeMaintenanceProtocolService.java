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
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.EiaTypeMaintenanceProtocolService")
public class EiaTypeMaintenanceProtocolService implements
		EiaTypeMaintenanceProtocolServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaTypeMaintenanceProtocolService.class
			.getName());
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			EiaTypeMaintenanceProtocol entity = em.find(EiaTypeMaintenanceProtocol.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete EiaTypeMaintenanceProtocol", e);
			throw new EJBException("Error eliminando EiaTypeMaintenanceProtocol por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#findByEiaTypeMaintenancePlan(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public List<EiaTypeMaintenanceProtocol> findByEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan maintenancePlan) throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenanceProtocol.findByEiaTypeMaintenancePlan", EiaTypeMaintenanceProtocol.class)
					.setParameter("eiaTypeMaintenancePlan", maintenancePlan).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding EiaTypeMaintenanceProtocol by MaintenancePlan", e);
			throw new EJBException("Error buscando EiaTypeMaintenanceProtocol por EiaTypeMaintenanceProtocol"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#findByEiaTypeMaintenancePlan(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan, int, int)
	 */
	@Override
	public List<EiaTypeMaintenanceProtocol> findByEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan maintenancePlan, int offset, int size)
			throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenanceProtocol.findByEiaTypeMaintenancePlan", EiaTypeMaintenanceProtocol.class)
					.setParameter("eiaTypeMaintenancePlan", maintenancePlan).setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding EiaTypeMaintenanceProtocol by MaintenancePlan", e);
			throw new EJBException("Error buscando EiaTypeMaintenanceProtocol por EiaTypeMaintenanceProtocol"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#find(long)
	 */
	@Override
	public EiaTypeMaintenanceProtocol find(long Id) throws EJBException {
		try {
			return em.find(EiaTypeMaintenanceProtocol.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding EiaTypeMaintenanceProtocol", e);
			throw new EJBException("ERROR: finding EiaTypeMaintenanceProtocol "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#getAll()
	 */
	@Override
	public List<EiaTypeMaintenanceProtocol> getAll() throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenanceProtocol.getAll", EiaTypeMaintenanceProtocol.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all EiaTypeMaintenanceProtocol", e);
			throw new EJBException("Error buscando todos los EiaTypeMaintenanceProtocol"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#getAll(int, int)
	 */
	@Override
	public List<EiaTypeMaintenanceProtocol> getAll(int offset, int size)
			throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenanceProtocol.getAll", EiaTypeMaintenanceProtocol.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all EiaTypeMaintenanceProtocol", e);
			throw new EJBException("Error buscando todos los EiaTypeMaintenanceProtocol"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#save(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol)
	 */
	@Override
	public EiaTypeMaintenanceProtocol save(
			EiaTypeMaintenanceProtocol maintenanceProtocol) throws EJBException {
		try {
			em.persist(maintenanceProtocol);
			em.flush();
			return em.find(EiaTypeMaintenanceProtocol.class, maintenanceProtocol.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaTypeMaintenanceProtocol ", e);
			throw new EJBException("ERROR: saving EiaTypeMaintenanceProtocol "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#update(org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol)
	 */
	@Override
	public EiaTypeMaintenanceProtocol update(
			EiaTypeMaintenanceProtocol maintenanceProtocol) throws EJBException {
		try {
			EiaTypeMaintenanceProtocol res = em.merge(maintenanceProtocol);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update EiaTypeMaintenanceProtocol ", e);
			throw new EJBException("ERROR: no se puede actualizar el EiaTypeMaintenanceProtocol "
					+ e.getCause().getMessage());
		}
	}

}
