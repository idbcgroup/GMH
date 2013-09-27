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
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol;

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.maintenanceSubProtocolService")
public class MaintenanceSubProtocolService implements
		MaintenanceSubProtocolServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaintenanceSubProtocolService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#delete(
	 * long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			MaintenanceSubProtocol entity = em.find(
					MaintenanceSubProtocol.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete MaintenanceSubProtocol", e);
			throw new EJBException(
					"Error eliminando MaintenanceSubProtocol por id "
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#
	 * findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<MaintenanceSubProtocol> findByMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) throws EJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceSubProtocol.findByProtocolActivity",
							MaintenanceSubProtocol.class)
					.setParameter("parentMaintenanceActivity",
							maintenanceActivity).getResultList();
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: finding ProtocolActivity by MaintenanceSubProtocol",
					e);
			throw new EJBException(
					"Error buscando ProtocolActivity por MaintenanceSubProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#find(long)
	 */
	@Override
	public MaintenanceSubProtocol find(long Id) throws EJBException {
		try {
			return em.find(MaintenanceSubProtocol.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaintenanceSubProtocol", e);
			throw new EJBException("ERROR: finding MaintenanceSubProtocol "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#getAll()
	 */
	@Override
	public List<MaintenanceSubProtocol> getAll() throws EJBException {
		try {
			return em.createNamedQuery("MaintenanceSubProtocol.getAll",
					MaintenanceSubProtocol.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceSubProtocol",
					e);
			throw new EJBException(
					"Error buscando todos los MaintenanceSubProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<MaintenanceSubProtocol> getAll(int offset, int size)
			throws EJBException {
		try {
			return em
					.createNamedQuery("MaintenanceSubProtocol.getAll",
							MaintenanceSubProtocol.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceSubProtocol",
					e);
			throw new EJBException(
					"Error buscando todos los MaintenanceSubProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#save(org
	 * .fourgeeks.gha.domain.gmh.MaintenanceSubProtocol)
	 */
	@Override
	public MaintenanceSubProtocol save(
			MaintenanceSubProtocol maintenanceSubProtocol) throws EJBException {
		try {
			em.persist(maintenanceSubProtocol);
			em.flush();
			return em.find(MaintenanceSubProtocol.class,
					maintenanceSubProtocol.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenanceSubProtocol ", e);
			throw new EJBException("ERROR: saving MaintenanceSubProtocol "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceSubProtocolServiceRemote#update(
	 * org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol)
	 */
	@Override
	public MaintenanceSubProtocol update(
			MaintenanceSubProtocol maintenanceSubProtocol) throws EJBException {
		try {
			em.merge(maintenanceSubProtocol);
			em.flush();
			return em.find(MaintenanceSubProtocol.class,
					maintenanceSubProtocol.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenanceSubProtocol ", e);
			throw new EJBException("ERROR: saving MaintenanceSubProtocol "
					+ e.getCause().getMessage());
		}
	}

}
