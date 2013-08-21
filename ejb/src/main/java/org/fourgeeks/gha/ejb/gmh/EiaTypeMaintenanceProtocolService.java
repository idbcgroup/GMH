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
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author emiliot
 * 
 */
@Stateless(name = "gmh.EiaTypeMaintenanceProtocolService")
public class EiaTypeMaintenanceProtocolService implements
		EiaTypeMaintenanceProtocolServiceRemote {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaintenanceProtocol.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#delete
	 * (long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			MaintenanceProtocol entity = em.find(
					MaintenanceProtocol.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete EiaTypeMaintenanceProtocol", e);
			throw new EJBException(
					"ERROR: unable to delete EiaTypeMaintenanceProtocol "
							+ e.getCause().getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#find
	 * (org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan)
	 */
	@Override
	public List<MaintenanceProtocol> findByEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		try {
			logger.log(Level.INFO, "DEBUG: " + eiaTypeMaintenancePlan.toString());
			List <MaintenanceProtocol> res = em.createNamedQuery(
							"EiaTypeMaintenanceProtocol.findByEiaTypeMaintenancePlan",
							MaintenanceProtocol.class)
					.setParameter("eiaTypeMaintenancePlan",
							eiaTypeMaintenancePlan).getResultList();
			
			return res;
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error retrieving EiaTypeMaintenanceProtocol by EiaTypeMaintenancePlan",
					ex);
			throw new EJBException(
					"Error obteniendo todos los EiaTypeMaintenanceProtocol por EiaTypeMaintenancePlan"
							+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#find
	 * (long)
	 */
	@Override
	public MaintenanceProtocol find(long Id) throws EJBException {
		try {
			return em.find(MaintenanceProtocol.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding EiaTypeMaintenanceProtocol",
					e);
			throw new EJBException("ERROR: finding EiaTypeMaintenanceProtocol "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#getAll
	 * ()
	 */
	@Override
	public List<MaintenanceProtocol> getAll() throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenanceProtocol.getAll",
					MaintenanceProtocol.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error retrieving all EiaTypeMaintenanceProtocol", ex);
			throw new EJBException(
					"Error obteniendo todas las EiaTypeMaintenanceProtocol"
							+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#save
	 * (org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol)
	 */
	@Override
	public MaintenanceProtocol save(
			MaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException {
		try {
			em.persist(eiaTypeMaintenanceProtocol);
			em.flush();
			return em.find(MaintenanceProtocol.class,
					eiaTypeMaintenanceProtocol.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiaTypeMaintenanceProtocol "
					+ eiaTypeMaintenanceProtocol.toString(), e);
			throw new EJBException("ERROR: saving eiaTypeMaintenanceProtocol "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeMaintenanceProtocolServiceRemote#update
	 * (org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol)
	 */
	@Override
	public MaintenanceProtocol update(
			MaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException {
		try {
			MaintenanceProtocol res = em
					.merge(eiaTypeMaintenanceProtocol);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update EiaTypeMaintenanceProtocol "
							+ eiaTypeMaintenanceProtocol.toString(), e);
			throw new EJBException(
					"ERROR: no se puede actualizar el EiaTypeMaintenanceProtocol "
							+ e.getCause().getMessage());
		}
	}

}
