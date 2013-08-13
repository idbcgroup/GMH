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
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypeMaintenanceProtocol.class.getName());

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
			EiaTypeMaintenanceProtocol entity = em.find(
					EiaTypeMaintenanceProtocol.class, Id);
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
	public List<EiaTypeMaintenanceProtocol> findByEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) throws EJBException {
		try {
			logger.log(Level.INFO, "DEBUG: " + eiaTypeMaintenancePlan.toString());
			List <EiaTypeMaintenanceProtocol> res = em.createNamedQuery(
							"EiaTypeMaintenanceProtocol.findByEiaTypeMaintenancePlan",
							EiaTypeMaintenanceProtocol.class)
					.setParameter("eiaTypeMaintenancePlan",
							eiaTypeMaintenancePlan).getResultList();
			
			//TODO: Armar el arbol de los protocolos de mantenimientos
			
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
	public EiaTypeMaintenanceProtocol find(long Id) throws EJBException {
		try {
			return em.find(EiaTypeMaintenanceProtocol.class, Id);
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
	public List<EiaTypeMaintenanceProtocol> getAll() throws EJBException {
		try {
			return em.createNamedQuery("EiaTypeMaintenanceProtocol.getAll",
					EiaTypeMaintenanceProtocol.class).getResultList();
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
	public EiaTypeMaintenanceProtocol save(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException {
		try {
			em.persist(eiaTypeMaintenanceProtocol);
			em.flush();
			return em.find(EiaTypeMaintenanceProtocol.class,
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
	public EiaTypeMaintenanceProtocol update(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException {
		try {
			EiaTypeMaintenanceProtocol res = em
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
