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
import org.fourgeeks.gha.domain.gmh.ServiceResource;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.serviceResourceService")
public class ServiceResourceService implements ServiceResourceServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ServiceResourceService.class.getName());

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			ServiceResource entity = em.find(ServiceResource.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Resource/Service",
					e);
			throw new EJBException("Error eliminando Resource/Service por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<ServiceResource> findByProtocolActivity(
			MaintenanceActivity protocolActivity) throws EJBException {
		try {
			return em.createNamedQuery("ServiceResource.findByProtocolActivity",
							ServiceResource.class)
					.setParameter("protocolActivity", protocolActivity).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Resources/Services by ProtocolActivity", e);
			throw new EJBException(
					"Error buscando Resources/Services por ProtocolActivity"
							+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#find(long)
	 */
	@Override
	public ServiceResource find(long Id) throws EJBException {
		try {
			return em.find(ServiceResource.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Resource/Service", e);
			throw new EJBException("ERROR: finding Resource/Service "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#getAll()
	 */
	@Override
	public List<ServiceResource> getAll() throws EJBException {
		try {
			return em.createNamedQuery("ServiceResource.getAll",
					ServiceResource.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource/Service", e);
			throw new EJBException("Error buscando todos los Resource/Service"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#getAll(int, int)
	 */
	@Override
	public List<ServiceResource> getAll(int offset, int size)
			throws EJBException {
		try {
			return em.createNamedQuery("ServiceResource.getAll",
					ServiceResource.class).setFirstResult(offset).setMaxResults(size)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource/Service", e);
			throw new EJBException("Error buscando todos los Resource/Service"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#save(org.fourgeeks.gha.domain.gmh.ServiceResource)
	 */
	@Override
	public ServiceResource save(ServiceResource ras) throws EJBException {
		try {
			em.persist(ras);
			em.flush();
			return em.find(ServiceResource.class, ras.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Resource/Service ", e);
			throw new EJBException("ERROR: saving Resource/Service "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#update(org.fourgeeks.gha.domain.gmh.ServiceResource)
	 */
	@Override
	public ServiceResource update(ServiceResource ras) throws EJBException {
		try {
			ServiceResource res = em.merge(ras);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Resource/Service ",
					e);
			throw new EJBException(
					"ERROR: no se puede actualizar el Resource/Service "
							+ e.getCause().getMessage());
		}
	}
}
