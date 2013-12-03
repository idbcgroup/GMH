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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless
public class MaintenanceActivityService extends GHAEJBExceptionImpl implements
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
	public void delete(long Id) throws GHAEJBException {
		try {
			MaintenanceActivity entity = em.find(MaintenanceActivity.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete MaintenanceActivity", e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-delete-fail",
					RuntimeParameters.getLang(), em);
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
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException {
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
			throw super.generateGHAEJBException(
					"maintenanceActivity-findByMaintenanceProtocol-fail",
					RuntimeParameters.getLang(), em);
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
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceActivity.findByMaintenanceProtocol",
							MaintenanceActivity.class)
					.setParameter("maintenanceProtocol", maintenanceProtocol)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenanceProtocol", e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-findByMaintenanceProtocol-fail",
					RuntimeParameters.getLang(), em);
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
			ServiceResource serviceResource) throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceActivity.findByServiceResource",
							MaintenanceActivity.class)
					.setParameter("serviceResource", serviceResource)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by Resource/Service", e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-findByServiceResource-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#find(long)
	 */
	@Override
	public MaintenanceActivity find(long Id) throws GHAEJBException {
		try {
			return em.find(MaintenanceActivity.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaintenanceActivity", e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#getAll()
	 */
	@Override
	public List<MaintenanceActivity> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenanceActivity.getAll",
					MaintenanceActivity.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceActivity", e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-getAll-fail",
					RuntimeParameters.getLang(), em);
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
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaintenanceActivity.getAll",
							MaintenanceActivity.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceActivity", e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-getAll-fail",
					RuntimeParameters.getLang(), em);
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
			throws GHAEJBException {
		try {
			em.persist(protocolActivity);
			em.flush();
			return em.find(MaintenanceActivity.class, protocolActivity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenanceActivity ", e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-save-fail",
					RuntimeParameters.getLang(), em);
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
			throws GHAEJBException {
		try {
			MaintenanceActivity res = em.merge(protocolActivity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update MaintenanceActivity ", e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceActivityServiceRemote#find(org.fourgeeks
	 * .gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public List<MaintenanceActivity> find(
			MaintenanceActivity maintenanceActivity) throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MaintenanceActivity> cQuery = cb
					.createQuery(MaintenanceActivity.class);
			Root<MaintenanceActivity> root = cQuery
					.from(MaintenanceActivity.class);

			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("name")));
			Predicate criteria = buildFilters(maintenanceActivity, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<MaintenanceActivity> q = em.createQuery(cQuery);

			if (maintenanceActivity.getName() != null)
				q.setParameter("name", "%"
						+ maintenanceActivity.getName().toLowerCase() + "%");
			if (maintenanceActivity.getDescription() != null)
				q.setParameter("description", "%"
						+ maintenanceActivity.getDescription().toLowerCase()
						+ "%");

			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los maintenancePlan por maintenancePlan",
					e);
			throw super.generateGHAEJBException(
					"maintenanceActivity-findByMaintenanceActivity-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/**
	 * @param maintenanceActivity
	 * @param cb
	 * @param root
	 * @return
	 */
	private Predicate buildFilters(MaintenanceActivity maintenanceActivity,
			CriteriaBuilder cb, Root<MaintenanceActivity> root) {
		Predicate predicate = cb.conjunction();
		if (maintenanceActivity.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("name")), p));
		}
		if (maintenanceActivity.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("description")), p));
		}
		return predicate;
	}
}
