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
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.MaintenancePlanService")
public class EiaTypeMaintenancePlanService implements EiaTypeMaintenancePlanServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaTypeMaintenancePlanService.class
			.getName());

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			EiaTypeMaintenancePlan entity = em.find(EiaTypeMaintenancePlan.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete MaintenancePlan", e);
			throw new EJBException("Error eliminando MaintenancePlan por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType) throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenancePlan.findByEiaType", EiaTypeMaintenancePlan.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenancePlan", e);
			throw new EJBException("Error buscando MaintenancePlan por EiaType"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> findByEiaType(EiaType eiaType, int offset, int size)
			throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenancePlan.findByEiaType", EiaTypeMaintenancePlan.class)
					.setParameter("eiaType", eiaType).setFirstResult(offset).setMaxResults(size)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenancePlan", e);
			throw new EJBException("Error buscando MaintenancePlan por EiaType"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(long)
	 */
	@Override
	public EiaTypeMaintenancePlan find(long Id) throws EJBException {
		try {
			return em.find(EiaTypeMaintenancePlan.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaintenancePlan", e);
			throw new EJBException("ERROR: finding MaintenancePlan "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#getAll()
	 */
	@Override
	public List<EiaTypeMaintenancePlan> getAll() throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenancePlan.getAll", EiaTypeMaintenancePlan.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenancePlan", e);
			throw new EJBException("Error buscando todos los MaintenancePlan"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#getAll(int, int)
	 */
	@Override
	public List<EiaTypeMaintenancePlan> getAll(int offset, int size) throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenancePlan.getAll", EiaTypeMaintenancePlan.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenancePlan", e);
			throw new EJBException("Error buscando todos los MaintenancePlan"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#save(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan save(EiaTypeMaintenancePlan maintenancePlan)
			throws EJBException {
		try {
			em.persist(maintenancePlan);
			em.flush();
			return em.find(EiaTypeMaintenancePlan.class, maintenancePlan.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenancePlan ", e);
			throw new EJBException("ERROR: saving MaintenancePlan "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#update(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public EiaTypeMaintenancePlan update(EiaTypeMaintenancePlan maintenancePlan)
			throws EJBException {
		try {
			EiaTypeMaintenancePlan res = em.merge(maintenancePlan);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update MaintenancePlan ", e);
			throw new EJBException("ERROR: no se puede actualizar el MaintenancePlan "
					+ e.getCause().getMessage());
		}
	}

}
