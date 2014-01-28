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

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless
public class MaintenanceActivityService extends GHAEJBExceptionService
		implements MaintenanceActivityServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaintenanceActivityService.class.getName());

	/**
	 * @param maintenanceActivity
	 * @param cb
	 * @param root
	 * @return
	 */
	private Predicate buildFilters(MaintenanceActivity maintenanceActivity,
			CriteriaBuilder cb, Root<MaintenanceActivity> root) {
		Predicate predicate = cb.conjunction();

		final Activity activity = maintenanceActivity.getActivity();

		if (activity.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("name")), p));
		}
		if (activity.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("description")), p));
		}
		if (activity.getCategory() != null) {
			ParameterExpression<ActivityCategoryEnum> p = cb.parameter(
					ActivityCategoryEnum.class, "category");
			predicate = cb.and(predicate,
					cb.equal(root.<ActivityCategoryEnum> get("category"), p));
		}
		if (activity.getSubCategory() != null) {
			ParameterExpression<ActivitySubCategoryEnum> p = cb.parameter(
					ActivitySubCategoryEnum.class, "subCategory");
			predicate = cb.and(predicate, cb.equal(
					root.<ActivitySubCategoryEnum> get("subCategory"), p));
		}
		if (activity.getIsSubProtocol() == true) {
			ParameterExpression<Boolean> p = cb.parameter(Boolean.class,
					"isSubProtocol");
			predicate = cb.and(predicate,
					cb.equal(root.<Boolean> get("isSubProtocol"), p));
		}
		return predicate;
	}

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

			final Activity activity = maintenanceActivity.getActivity();
			if (activity.getName() != null)
				q.setParameter("name", "%" + activity.getName().toLowerCase()
						+ "%");
			if (activity.getDescription() != null)
				q.setParameter("description", "%"
						+ activity.getDescription().toLowerCase() + "%");
			if (activity.getCategory() != null)
				q.setParameter("category", activity.getCategory());

			if (activity.getSubCategory() != null)
				q.setParameter("subCategory", activity.getSubCategory());

			if (activity.getIsSubProtocol() == true) {
				q.setParameter("isSubProtocol", activity.getIsSubProtocol());
			}

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
	public MaintenanceActivity save(MaintenanceActivity entity)
			throws GHAEJBException {
		try {
			em.persist(entity.getActivity());
			em.persist(entity);
			em.flush();
			return em.find(MaintenanceActivity.class, entity.getId());
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
	public MaintenanceActivity update(MaintenanceActivity entity)
			throws GHAEJBException {
		try {
			em.merge(entity.getActivity());
			MaintenanceActivity res = em.merge(entity);
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
}
