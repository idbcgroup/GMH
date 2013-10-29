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
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "glm.MaterialCategoryService")
public class MaterialCategoryService extends GHAEJBExceptionImpl implements
		MaterialCategoryServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaterialCategoryService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#delete(long)
	 */
	@Override
	public void delete(String code) throws GHAEJBException {
		try {
			MaterialCategory entity = em.find(MaterialCategory.class, code);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete MaterialCategory",
					e);
			throw super.generateGHAEJBException("materialCategory-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	final private static Predicate buildFilters(
			MaterialCategory materialCategory, CriteriaBuilder cb,
			Root<MaterialCategory> root) {
		Predicate predicate = cb.conjunction();

		if (materialCategory.getType() != null) {
			ParameterExpression<MaterialTypeEnum> p = cb.parameter(
					MaterialTypeEnum.class, "type");
			predicate = cb.and(predicate,
					cb.equal(root.<MaterialTypeEnum> get("type"), p));
		}

		if (materialCategory.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("description")), p));
		}

		// if (materialCategory.getBrand() != null) {
		// ParameterExpression<Brand> p = cb.parameter(Brand.class, "brand");
		// predicate = cb.and(predicate,
		// cb.equal(root.<Brand> get("brand"), p));
		// }

		if (materialCategory.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("name")), p));
		}

		if (materialCategory.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("code"), p));
		}

		if (materialCategory.getExternalCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"extCode");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("extCode"), p));
		}

		if (materialCategory.getModel() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "model");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("model")), p));
		}

		return predicate;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.glm.MaterialCategoryServiceRemote#find(org.fourgeeks
	 * .gha.domain.glm.MaterialCategory)
	 */
	@Override
	public List<MaterialCategory> find(MaterialCategory entity)
			throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MaterialCategory> cQuery = cb
					.createQuery(MaterialCategory.class);
			Root<MaterialCategory> root = cQuery.from(MaterialCategory.class);

			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("name")));
			Predicate criteria = buildFilters(entity, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<MaterialCategory> q = em.createQuery(cQuery);

			if (entity.getType() != null)
				q.setParameter("type", entity.getType());

			if (entity.getDescription() != null)
				q.setParameter("description", "%"
						+ entity.getDescription().toLowerCase() + "%");
			//
			// if (entity.getBrand() != null)
			// q.setParameter("brand", entity.getBrand());

			if (entity.getName() != null)
				q.setParameter("name", "%" + entity.getName().toLowerCase()
						+ "%");

			if (entity.getCode() != null)
				q.setParameter("code", entity.getCode());

			if (entity.getExternalCode() != null)
				q.setParameter("extCode", entity.getExternalCode());

			if (entity.getModel() != null)
				q.setParameter("model", "%" + entity.getModel().toLowerCase()
						+ "%");

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
	public MaterialCategory find(String code) throws GHAEJBException {
		try {
			return em.find(MaterialCategory.class, code);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaterialCategory", e);
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
	public List<MaterialCategory> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("MaterialCategory.getAll",
					MaterialCategory.class).getResultList();
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
	public List<MaterialCategory> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaterialCategory.getAll",
							MaterialCategory.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
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
	public MaterialCategory save(MaterialCategory materialCategory)
			throws GHAEJBException {
		try {
			em.persist(materialCategory);
			em.flush();
			return em.find(MaterialCategory.class, materialCategory.getCode());
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
	public MaterialCategory update(MaterialCategory materialCategory)
			throws GHAEJBException {
		try {
			MaterialCategory res = em.merge(materialCategory);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update MaterialCategory ",
					e);
			throw super.generateGHAEJBException("materialCategory-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<MaterialCategory> getAllUtilities() throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaterialCategory.getByType",
							MaterialCategory.class)
					.setParameter("materialTypeId", MaterialTypeEnum.UTILITARIO)
					.getResultList();
		} catch (Exception ex) {
			logger.log(
					Level.SEVERE,
					"Error retrieving all MaterialsCategories which are Utilities",
					ex);
			// TODO corregir mensaje de retorno, este me lo copie
			throw super.generateGHAEJBException("material-getByType-fail",
					RuntimeParameters.getLang(), em);
		}

	}
}
