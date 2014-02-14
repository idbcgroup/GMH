package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * 
 * @author caparicio
 * 
 */
@Stateless
public class RequiredResourcesService extends GHAEJBExceptionService implements
		RequiredResourcesServiceRemote {

	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(RequiredResourcesService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.RequiredResourcesServiceRemote#delete(long)
	 */
	@Override
	public void delete(long id) throws GHAEJBException {
		try {
			RequiredResources entity = em.find(RequiredResources.class, id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete RequiredResources",
					e);
			throw super.generateGHAEJBException(
					"requiredResources-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.RequiredResourcesServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.RequiredResources)
	 */
	@Override
	public RequiredResources save(RequiredResources requiredResources)
			throws GHAEJBException {
		try {
			em.persist(requiredResources);
			em.flush();
			return em.find(RequiredResources.class, requiredResources.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving RequiredResources ", e);
			throw super.generateGHAEJBException("requiredResources-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.RequiredResourcesServiceRemote#
	 * findByServiceAndResource(org.fourgeeks.gha.domain.gmh.ServiceAndResource)
	 */
	@Override
	public List<RequiredResources> findByServiceAndResource(
			ServiceAndResource resource) throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"RequiredResources.findByServiceAndResource",
							RequiredResources.class)
					.setParameter("resource", resource).getResultList();
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error retriving all RequiredResources by serviceAndResource",
					ex);
			throw super.generateGHAEJBException(
					"requiredResources-findByServiceAndResource-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public void delete(List<RequiredResources> requiredResources)
			throws GHAEJBException {
		try {
			for (RequiredResources rr : requiredResources) {
				RequiredResources entity = em.find(RequiredResources.class,
						rr.getId());
				em.remove(entity);
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete RequiredResources",
					e);
			throw super.generateGHAEJBException(
					"requiredResources-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<RequiredResources> findEiaTypeByActivity(Activity activity)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"RequiredResources.findEiaTypeByActivity",
							RequiredResources.class)
					.setParameter("activity", activity).getResultList();
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error retriving all RequiredResources by findEiaTypeByActivity",
					ex);
			throw super.generateGHAEJBException(
					"requiredResources-findByActivity-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<RequiredResources> findMaterialByActivity(Activity activity)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"RequiredResources.findMaterialByActivity",
							RequiredResources.class)
					.setParameter("activity", activity).getResultList();
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error retriving all RequiredResources by findMaterialByActivity",
					ex);
			throw super.generateGHAEJBException(
					"requiredResources-findByActivity-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public RequiredResources update(RequiredResources requiredResources)
			throws GHAEJBException {
		try {
			em.merge(requiredResources);
			em.flush();
			return em.find(RequiredResources.class, requiredResources.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: updating RequiredResources ", e);
			throw super.generateGHAEJBException(
					"requiredResources-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}