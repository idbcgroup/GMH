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
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "gmh.serviceResourceService")
public class ServiceResourceService extends GHAEJBExceptionImpl implements
		ServiceResourceServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ServiceResourceService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			ServiceResource entity = em.find(ServiceResource.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Resource/Service",
					e);
			throw super.generateGHAEJBException("serviceResource-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#findByProtocolActivity
	 * (org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<ServiceResource> findByProtocolActivity(
			MaintenanceActivity protocolActivity) throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"ServiceResource.findByMaintenanceActivity",
							ServiceResource.class)
					.setParameter("maintenanceActivity", protocolActivity)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding Resources/Services by MaintenanceActivity",
					e);
			throw super.generateGHAEJBException(
					"serviceResource-findByMaintenanceActivity-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#find(long)
	 */
	@Override
	public ServiceResource find(long Id) throws GHAEJBException {
		try {
			return em.find(ServiceResource.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Resource/Service", e);
			throw super.generateGHAEJBException("serviceResource-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#getAll()
	 */
	@Override
	public List<ServiceResource> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("ServiceResource.getAll",
					ServiceResource.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource/Service", e);
			throw super.generateGHAEJBException("serviceResource-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<ServiceResource> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("ServiceResource.getAll",
							ServiceResource.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource/Service", e);
			throw super.generateGHAEJBException("serviceResource-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.ServiceResource)
	 */
	@Override
	public ServiceResource save(ServiceResource ras) throws GHAEJBException {
		try {
			em.persist(ras);
			em.flush();
			return em.find(ServiceResource.class, ras.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Resource/Service ", e);
			throw super.generateGHAEJBException("serviceResource-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#update(org.fourgeeks
	 * .gha.domain.gmh.ServiceResource)
	 */
	@Override
	public ServiceResource update(ServiceResource ras) throws GHAEJBException {
		try {
			ServiceResource res = em.merge(ras);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Resource/Service ",
					e);
			throw super.generateGHAEJBException("serviceResource-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}