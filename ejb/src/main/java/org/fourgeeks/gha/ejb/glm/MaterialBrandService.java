/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
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
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.gom.CCDIServiceLocal;

/**
 * @author emiliot
 * 
 */
@Stateless
public class MaterialBrandService extends GHAEJBExceptionService implements
		MaterialBrandServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaterialBrandService.class.getName());

	private static Predicate buildFilters(MaterialBrand materialBrand,
			CriteriaBuilder cb, Root<MaterialBrand> root) {
		Predicate predicate = cb.conjunction();

		final Material material = materialBrand.getMaterial();
		if (materialBrand.getBrand() != null) {
			ParameterExpression<Brand> p = cb.parameter(Brand.class, "brand");
			predicate = cb.and(predicate,
					cb.equal(root.<Brand> get("brand"), p));
		}

		if (material.getType() != null) {
			ParameterExpression<MaterialTypeEnum> p = cb.parameter(
					MaterialTypeEnum.class, "type");
			predicate = cb.and(predicate, cb.equal(root.get("material")
					.<MaterialTypeEnum> get("type"), p));
		}

		if (material.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			predicate = cb.and(
					predicate,
					cb.like(cb.lower(root.get("material").<String> get(
							"description")), p));
		}

		if (material.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			predicate = cb.and(predicate, cb.like(
					cb.lower(root.get("material").<String> get("name")), p));
		}

		if (material.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			predicate = cb.and(predicate,
					cb.equal(root.get("material").<String> get("code"), p));
		}

		if (material.getExternalCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"extCode");
			predicate = cb.and(predicate, cb.equal(root.get("material")
					.<String> get("externalCode"), p));
		}

		if (material.getModel() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "model");
			predicate = cb.and(predicate, cb.like(
					cb.lower(root.get("material").<String> get("model")), p));
		}

		if (material.getMaterialCategory() != null) {
			ParameterExpression<MaterialCategory> p = cb.parameter(
					MaterialCategory.class, "category");
			predicate = cb.and(
					predicate,
					cb.equal(
							root.get("material").<MaterialCategory> get(
									"materialCategory"), p));
		}

		return predicate;
	}

	@EJB
	CCDIServiceLocal ccdiService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			MaterialBrand entity = em.find(MaterialBrand.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete MaterialBrand", e);
			throw super
					.generateGHAEJBException("materialbrand-delete-fail", em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#find(long)
	 */
	@Override
	public MaterialBrand find(long Id) throws GHAEJBException {
		try {
			return em.find(MaterialBrand.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaterialBrand", e);
			throw super.generateGHAEJBException("materialbrand-find-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#find(org.fourgeeks
	 * .gha.domain.glm.MaterialBrand)
	 */
	@Override
	public List<MaterialBrand> find(MaterialBrand materialBrand)
			throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MaterialBrand> cQuery = cb
					.createQuery(MaterialBrand.class);
			Root<MaterialBrand> root = cQuery.from(MaterialBrand.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("material").<String> get("name")));

			Predicate criteria = buildFilters(materialBrand, cb, root);
			TypedQuery<MaterialBrand> q;
			final Material material = materialBrand.getMaterial();

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (materialBrand.getBrand() != null) {
					q.setParameter("brand", materialBrand.getBrand());
				}

				if (material.getCode() != null) {
					q.setParameter("code", material.getCode());
				}
				if (material.getExternalCode() != null) {
					q.setParameter("extCode", material.getExternalCode());
				}
				if (material.getType() != null) {
					q.setParameter("type", material.getType());
				}
				if (material.getDescription() != null) {
					q.setParameter("description", "%"
							+ material.getDescription().toLowerCase() + "%");
				}
				if (material.getName() != null) {
					q.setParameter("name", "%"
							+ material.getName().toLowerCase() + "%");
				}
				if (material.getModel() != null) {
					q.setParameter("model", "%"
							+ material.getModel().toLowerCase() + "%");
				}
				if (material.getMaterialCategory() != null) {
					q.setParameter("category", material.getMaterialCategory());
				}

			}
			return q.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los MaterialBrand por materialBrand", e);
			throw super.generateGHAEJBException("materialbrand-find-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#findByBrand(org.
	 * fourgeeks.gha.domain.gmh.Brand)
	 */
	@Override
	public List<MaterialBrand> findByBrand(Brand brand) throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaterialBrand.findByBrand",
							MaterialBrand.class).setParameter("brand", brand)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: Getting MaterialBrand", e);
			throw super.generateGHAEJBException("materialbrand-find-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#findByMaterialType
	 * (org.fourgeeks.gha.domain.glm.MaterialTypeEnum)
	 */
	@Override
	public List<MaterialBrand> findByMaterialType(MaterialTypeEnum type)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaterialBrand.findByType",
							MaterialBrand.class).setParameter("type", type)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: Getting MaterialBrand", e);
			throw super.generateGHAEJBException("materialbrand-find-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#getAll()
	 */
	@Override
	public List<MaterialBrand> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("MaterialBrand.getAll",
					MaterialBrand.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: Getting MaterialBrand", e);
			throw super.generateGHAEJBException("materialbrand-get-all-fail",
					em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<MaterialBrand> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaterialBrand.getAll",
							MaterialBrand.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: Getting MaterialBrand", e);
			throw super.generateGHAEJBException("materialbrand-get-all-fail",
					em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#save(org.fourgeeks
	 * .gha.domain.glm.MaterialBrand)
	 */
	@Override
	public MaterialBrand save(MaterialBrand materialBrand)
			throws GHAEJBException {
		try {
			Brand brand = materialBrand.getBrand();
			brand = em.find(Brand.class, brand.getId());
			materialBrand.setBrand(brand);

			final Material material = materialBrand.getMaterial();
			if (material != null) {
				material.setCode(ccdiService.getNextElementCode(material
						.getMaterialCategory().getCode()));
				em.persist(material);
			}

			em.persist(materialBrand);
			em.flush();

			return em.find(MaterialBrand.class, materialBrand.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: Saving MaterialBrand", e);
			throw super.generateGHAEJBException("materialbrand-save-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote#update(org.fourgeeks
	 * .gha.domain.glm.MaterialBrand)
	 */
	@Override
	public MaterialBrand update(MaterialBrand materialBrand)
			throws GHAEJBException {
		try {
			final Material material = materialBrand.getMaterial();
			if (material != null) {
				em.merge(material);
			}

			materialBrand = em.merge(materialBrand);
			return materialBrand;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: Updating MaterialBrand", e);
			throw super
					.generateGHAEJBException("materialbrand-update-fail", em);
		}
	}

}
