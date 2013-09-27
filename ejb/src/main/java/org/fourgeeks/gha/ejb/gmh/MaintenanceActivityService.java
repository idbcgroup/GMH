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
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ServiceResource;

/**
 * @author emiliot
 * 
 */
@Stateless(name = "gmh.maintenanceActivityService")
public class MaintenanceActivityService implements
		MaintenanceActivityServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaintenanceActivityService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			MaintenanceActivity entity = em.find(MaintenanceActivity.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete MaintenanceActivity", e);
			throw new EJBException(
					"Error eliminando MaintenanceActivity por id "
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#
	 * findByEiaTypeMaintenanceProtocol
	 * (org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public List<MaintenanceActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) throws EJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceActivity.findByMaintenanceProtocol",
							MaintenanceActivity.class)
					.setParameter("maintenanceProtocol", maintenanceProtocol)
					.getResultList();
		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: finding MaintenanceActivity by MaintenanceProtocol",
					e);
			throw new EJBException(
					"Error buscando MaintenanceActivity por MaintenanceProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#
	 * findByEiaTypeMaintenanceProtocol
	 * (org.fourgeeks.gha.domain.gmh.MaintenanceProtocol, int, int)
	 */
	@Override
	public List<MaintenanceActivity> findByMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol, int offset, int size)
			throws EJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceActivity.findByMaintenanceProtocol",
							MaintenanceActivity.class)
					.setParameter("maintenanceProtocol", maintenanceProtocol)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenanceProtocol", e);
			throw new EJBException(
					"Error buscando MaintenanceActivity por MaintenanceProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#findByResource
	 * (org.fourgeeks.gha.domain.gmh.Resource)
	 */
	@Override
	public List<MaintenanceActivity> findByServiceResource(
			ServiceResource serviceResource) throws EJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceActivity.findByServiceResource",
							MaintenanceActivity.class)
					.setParameter("serviceResource", serviceResource)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by Resource/Service", e);
			throw new EJBException(
					"Error buscando MaintenanceActivity por Resource/Service"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#find(long)
	 */
	@Override
	public MaintenanceActivity find(long Id) throws EJBException {
		try {
			return em.find(MaintenanceActivity.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaintenanceActivity", e);
			throw new EJBException("ERROR: finding MaintenanceActivity "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#getAll()
	 */
	@Override
	public List<MaintenanceActivity> getAll() throws EJBException {
		try {
			return em.createNamedQuery("MaintenanceActivity.getAll",
					MaintenanceActivity.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceActivity", e);
			throw new EJBException(
					"Error buscando todos los MaintenanceActivity"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<MaintenanceActivity> getAll(int offset, int size)
			throws EJBException {
		try {
			return em
					.createNamedQuery("MaintenanceActivity.getAll",
							MaintenanceActivity.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceActivity", e);
			throw new EJBException(
					"Error buscando todos los MaintenanceActivity"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public MaintenanceActivity save(MaintenanceActivity protocolActivity)
			throws EJBException {
		try {
			em.persist(protocolActivity);
			em.flush();
			return em.find(MaintenanceActivity.class, protocolActivity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenanceActivity ", e);
			throw new EJBException("ERROR: saving MaintenanceActivity "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#update(org
	 * .fourgeeks .gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public MaintenanceActivity update(MaintenanceActivity protocolActivity)
			throws EJBException {
		try {
			MaintenanceActivity res = em.merge(protocolActivity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update MaintenanceActivity ", e);
			throw new EJBException(
					"ERROR: no se puede actualizar el MaintenanceActivity "
							+ e.getCause().getMessage());
		}
	}
}
