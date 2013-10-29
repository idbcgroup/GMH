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

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret, vivi.torresg
 * 
 */
@Stateless(name = "glm.MaterialService")
public class MaterialService extends GHAEJBExceptionImpl implements
		MaterialServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(MaterialService.class
			.getName());

	// private static Predicate buildFilters(Material material,
	// CriteriaBuilder cb, Join<Material, MaterialCategory> category) {
	// Predicate predicate = cb.conjunction();
	//
	// if (material.getType() != null) {
	// ParameterExpression<MaterialTypeEnum> p = cb.parameter(
	// MaterialTypeEnum.class, "type");
	// predicate = cb.and(predicate,
	// cb.equal(category.<MaterialTypeEnum> get("type"), p));
	// }
	//
	// if (material.getDescription() != null) {
	// ParameterExpression<String> p = cb.parameter(String.class,
	// "description");
	// predicate = cb.and(predicate,
	// cb.like(cb.lower(category.<String> get("description")), p));
	// }
	//
	// if (material.getBrand() != null) {
	// ParameterExpression<Brand> p = cb.parameter(Brand.class, "brand");
	// predicate = cb.and(predicate,
	// cb.equal(category.<Brand> get("brand"), p));
	// }
	//
	// if (material.getName() != null) {
	// ParameterExpression<String> p = cb.parameter(String.class, "name");
	// predicate = cb.and(predicate,
	// cb.like(cb.lower(category.<String> get("name")), p));
	// }
	//
	// if (material.getCode() != null) {
	// ParameterExpression<String> p = cb.parameter(String.class, "code");
	// predicate = cb.and(predicate,
	// cb.equal(category.<String> get("code"), p));
	// }
	//
	// if (material.getExternalCode() != null) {
	// ParameterExpression<String> p = cb.parameter(String.class,
	// "extCode");
	// predicate = cb.and(predicate,
	// cb.equal(category.<String> get("extCode"), p));
	// }
	//
	// if (material.getModel() != null) {
	// ParameterExpression<String> p = cb.parameter(String.class, "model");
	// predicate = cb.and(predicate,
	// cb.like(cb.lower(category.<String> get("model")), p));
	// }
	//
	// return predicate;
	//
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Material entity = em.find(Material.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Material", e);
			throw super.generateGHAEJBException("material-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#find(long)
	 */
	@Override
	public Material find(long Id) throws GHAEJBException {
		try {
			return em.find(Material.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Material", e);
			throw super.generateGHAEJBException("material-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#find(org.fourgeeks.gha
	 * .domain .gmh.Material)
	 */
	@Override
	public List<Material> find(Material entity) throws GHAEJBException {
		// TODO
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#getAll()
	 */
	@Override
	public List<Material> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Material.getAll", Material.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Materials", ex);
			throw super.generateGHAEJBException("material-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#getAll()
	 */
	@Override
	public List<Material> getAll(int offset, int size) throws GHAEJBException {
		try {
			return em.createNamedQuery("Material.getAll", Material.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Materials", ex);
			throw super.generateGHAEJBException("material-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<Material> getAllUtilities() throws GHAEJBException {
		try {
			return em
					.createNamedQuery("Material.getByType", Material.class)
					.setParameter("materialTypeId", MaterialTypeEnum.UTILITARIO)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error retrieving all Materials which are Utilities", ex);
			throw super.generateGHAEJBException("material-getByType-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	@Override
	public List<Material> getAllUtilities(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("Material.getByType", Material.class)
					.setParameter("materialTypeId", MaterialTypeEnum.UTILITARIO)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error retrieving all Materials which are Utilities", ex);
			throw super.generateGHAEJBException("material-getByType-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#save(org.fourgeeks.gha
	 * .domain .gmh.Material)
	 */
	@Override
	public Material save(Material material) throws GHAEJBException {
		try {
			em.persist(material);
			em.flush();
			return em.find(Material.class, material.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Material ", e);
			throw super.generateGHAEJBException("material-save-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#update(org.fourgeeks.gha
	 * .domain.gmh.Material)
	 */
	@Override
	public Material update(Material material) throws GHAEJBException {
		try {
			Material res = em.merge(material);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Material ", e);
			throw super.generateGHAEJBException("material-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#findByBrand(org.fourgeeks
	 * .gha.domain.gmh.Brand)
	 */
	@Override
	public List<Material> findByBrand(Brand brand) throws GHAEJBException {
		try {
			return em.createNamedQuery("Material.findByBrand", Material.class)
					.setParameter("brand", brand).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error retrieving all Materials filtered by brand", ex);
			throw super.generateGHAEJBException("material-findByBrand-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
