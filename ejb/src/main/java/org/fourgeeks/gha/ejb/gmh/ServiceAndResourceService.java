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

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class ServiceAndResourceService extends GHAEJBExceptionService implements
		ServiceAndResourceServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ServiceAndResourceService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			ServiceAndResource entity = em.find(ServiceAndResource.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Resource/Service",
					e);
			throw super.generateGHAEJBException(
					"serviceAndResource-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#find(long)
	 */
	@Override
	public ServiceAndResource find(long Id) throws GHAEJBException {
		try {
			return em.find(ServiceAndResource.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Resource/Service", e);
			throw super.generateGHAEJBException("serviceAndResource-find-fail",
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
	public List<ServiceAndResource> findByActivity(Activity activity)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("ServiceAndResource.findByActivity",
							ServiceAndResource.class)
					.setParameter("activity", activity).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding Resources/Services by Activity", e);
			throw super.generateGHAEJBException(
					"serviceAndResource-findByActivity-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.ServiceResourceServiceRemote#getAll()
	 */
	@Override
	public List<ServiceAndResource> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("ServiceAndResource.getAll",
					ServiceAndResource.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource/Service", e);
			throw super.generateGHAEJBException(
					"serviceAndResource-getAll-fail",
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
	public List<ServiceAndResource> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("ServiceAndResource.getAll",
							ServiceAndResource.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource/Service", e);
			throw super.generateGHAEJBException(
					"serviceAndResource-getAll-fail",
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
	public ServiceAndResource save(ServiceAndResource ras)
			throws GHAEJBException {
		try {
			em.persist(ras);
			em.flush();
			return em.find(ServiceAndResource.class, ras.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Resource/Service ", e);
			throw super.generateGHAEJBException("serviceAndResource-save-fail",
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
	public ServiceAndResource update(ServiceAndResource ras)
			throws GHAEJBException {
		try {
			ServiceAndResource res = em.merge(ras);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Resource/Service ",
					e);
			throw super.generateGHAEJBException(
					"serviceAndResource-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}