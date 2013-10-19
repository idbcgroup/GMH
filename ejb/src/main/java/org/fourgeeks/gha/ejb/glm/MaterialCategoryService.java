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
import org.fourgeeks.gha.domain.glm.MaterialCategory;
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
	public void delete(long Id) throws GHAEJBException {
		try {
			MaterialCategory entity = em.find(MaterialCategory.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete MaterialCategory",
					e);
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
	public List<MaterialCategory> find(MaterialCategory materialCategory)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaterialCategory.findByMaterialCategory",
							MaterialCategory.class)
					.setParameter("materialCategory", materialCategory)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error finding MaterialCategories by materialCategory", ex);
			throw super.generateGHAEJBException(
					"materialCategory-findByMaterialCategory-fail",
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
	public MaterialCategory find(long Id) throws GHAEJBException {
		try {
			return em.find(MaterialCategory.class, Id);
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
			return em.find(MaterialCategory.class, materialCategory.getId());
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
}
