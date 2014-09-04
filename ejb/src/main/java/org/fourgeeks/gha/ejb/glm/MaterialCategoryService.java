/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

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
import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class MaterialCategoryService extends GHAEJBExceptionService implements
		MaterialCategoryServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaterialCategoryService.class.getName());

	final private static Predicate buildFilters(
			ServicesResourceCategory servicesResourceCategory,
			CriteriaBuilder cb, Root<ServicesResourceCategory> root) {
		Predicate predicate = cb.conjunction();

		if (servicesResourceCategory.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("code"), p));
		}
		return predicate;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#delete(long)
	 */
	@Override
	public void delete(String code) throws GHAEJBException {
		try {
			ServicesResourceCategory entity = em.find(
					ServicesResourceCategory.class, code);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete ServicesResourceCategory", e);
			throw super.generateGHAEJBException("materialCategory-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#find(org.fourgeeks
	 * .gha.domain.glm.MaterialCategory)
	 */
	@Override
	public List<ServicesResourceCategory> find(ServicesResourceCategory entity)
			throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<ServicesResourceCategory> cQuery = cb
					.createQuery(ServicesResourceCategory.class);
			Root<ServicesResourceCategory> root = cQuery
					.from(ServicesResourceCategory.class);

			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("name")));
			Predicate criteria = buildFilters(entity, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<ServicesResourceCategory> q = em.createQuery(cQuery);

			if (entity.getCode() != null)
				q.setParameter("code", entity.getCode());

			return q.getResultList();

		} catch (Exception e) {
			logger.log(
					Level.SEVERE,
					"Error obteniendo los materialsCategory por materialsCAtegory",
					e);
			// TODO corregir mensaje de retorno, este me lo copie
			throw super.generateGHAEJBException("material-findByMaterial-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#find(long)
	 */
	@Override
	public ServicesResourceCategory find(String code) throws GHAEJBException {
		try {
			return em.find(ServicesResourceCategory.class, code);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding ServicesResourceCategory", e);
			throw super.generateGHAEJBException("materialCategory-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#getAll()
	 */
	@Override
	public List<ServicesResourceCategory> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("ServicesResourceCategory.getAll",
					ServicesResourceCategory.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all MaterialCategories",
					ex);
			throw super.generateGHAEJBException("materialCategory-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<ServicesResourceCategory> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("ServicesResourceCategory.getAll",
							ServicesResourceCategory.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all MaterialCategories",
					ex);
			throw super.generateGHAEJBException("materialCategory-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#save(org.fourgeeks
	 * .gha.domain.glm.MaterialCategory)
	 */
	@Override
	public ServicesResourceCategory save(
			ServicesResourceCategory servicesResourceCategory)
			throws GHAEJBException {
		try {
			em.persist(servicesResourceCategory);
			em.flush();
			return em.find(ServicesResourceCategory.class,
					servicesResourceCategory.getCode());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving materialCategory ", e);
			throw super.generateGHAEJBException("materialCategory-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#update(org
	 * .fourgeeks.gha.domain.glm.MaterialCategory)
	 */
	@Override
	public ServicesResourceCategory update(
			ServicesResourceCategory servicesResourceCategory)
			throws GHAEJBException {
		try {
			ServicesResourceCategory res = em.merge(servicesResourceCategory);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update ServicesResourceCategory ", e);
			throw super.generateGHAEJBException("materialCategory-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
