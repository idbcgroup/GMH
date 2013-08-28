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
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.Resource;

/**
 * @author emiliot
 * 
 */
@Stateless(name = "gmh.protocolActivityService")
public class ProtocolActivityService implements ProtocolActivityServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ProtocolActivityService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			ProtocolActivity entity = em.find(ProtocolActivity.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete ProtocolActivity",
					e);
			throw new EJBException("Error eliminando ProtocolActivity por id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#
	 * findByEiaTypeMaintenanceProtocol
	 * (org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol)
	 */
	@Override
	public List<ProtocolActivity> findByEiaTypeMaintenanceProtocol(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol)
			throws EJBException {
		try {
			return em
					.createNamedQuery(
							"ProtocolActivity.findByEiaTypeMaintenanceProtocol",
							ProtocolActivity.class)
					.setParameter("maintenanceProtocol",
							eiaTypeMaintenanceProtocol).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding ProtocolActivity by EiaTypeMaintenanceProtocol", e);
			throw new EJBException(
					"Error buscando ProtocolActivity por EiaTypeMaintenanceProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#
	 * findByEiaTypeMaintenanceProtocol
	 * (org.fourgeeks.gha.domain.gmh.EiaTypeMaintenanceProtocol, int, int)
	 */
	@Override
	public List<ProtocolActivity> findByEiaTypeMaintenanceProtocol(
			EiaTypeMaintenanceProtocol eiaTypeMaintenanceProtocol, int offset,
			int size) throws EJBException {
		try {
			return em
					.createNamedQuery(
							"ProtocolActivity.findByEiaTypeMaintenanceProtocol",
							ProtocolActivity.class)
					.setParameter("maintenanceProtocol",
							eiaTypeMaintenanceProtocol).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by ProtocolActivity", e);
			throw new EJBException(
					"Error buscando ProtocolActivity por EiaTypeMaintenanceProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#findByResource
	 * (org.fourgeeks.gha.domain.gmh.Resource)
	 */
	@Override
	public List<ProtocolActivity> findByResource(Resource resource)
			throws EJBException {
		try {
			return em.createNamedQuery("ProtocolActivity.findByResource",
							ProtocolActivity.class)
					.setParameter("resource", resource).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by Resource", e);
			throw new EJBException(
					"Error buscando ProtocolActivity por Resource"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#find(long)
	 */
	@Override
	public ProtocolActivity find(long Id) throws EJBException {
		try {
			return em.find(ProtocolActivity.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding ProtocolActivity", e);
			throw new EJBException("ERROR: finding ProtocolActivity "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#getAll()
	 */
	@Override
	public List<ProtocolActivity> getAll() throws EJBException {
		try {
			return em.createNamedQuery("ProtocolActivity.getAll",
					ProtocolActivity.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all ProtocolActivity", e);
			throw new EJBException("Error buscando todos los ProtocolActivity"
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<ProtocolActivity> getAll(int offset, int size)
			throws EJBException {
		try {
			return em
					.createNamedQuery("ProtocolActivity.getAll",
							ProtocolActivity.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all ProtocolActivity", e);
			throw new EJBException("Error buscando todos los ProtocolActivity"
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public ProtocolActivity save(ProtocolActivity protocolActivity)
			throws EJBException {
		try {
			em.persist(protocolActivity);
			em.flush();
			return em.find(ProtocolActivity.class, protocolActivity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving ProtocolActivity ", e);
			throw new EJBException("ERROR: saving ProtocolActivity "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ProtocolActivityServiceRemote#update(org.fourgeeks
	 * .gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public ProtocolActivity update(ProtocolActivity protocolActivity)
			throws EJBException {
		try {
			ProtocolActivity res = em.merge(protocolActivity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update ProtocolActivity ",
					e);
			throw new EJBException(
					"ERROR: no se puede actualizar el ProtocolActivity "
							+ e.getCause().getMessage());
		}
	}
}
