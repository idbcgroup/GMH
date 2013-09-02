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

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;

/**
 * @author alacret
 * 
 */
@Stateless(name = "gmh.MaterialService")
public class MaterialService implements MaterialServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(MaterialService.class
			.getName());

	private static Predicate buildFilters(Material material,
			CriteriaBuilder cb, Root<Material> root) {
		Predicate predicate = cb.conjunction();

		if (material.getType() != null) {
			ParameterExpression<MaterialTypeEnum> p = cb.parameter(
					MaterialTypeEnum.class, "type");
			predicate = cb.and(predicate,
					cb.equal(root.<MaterialTypeEnum> get("type"), p));
		}

		if (material.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("description")), p));
		}

		if (material.getExternalProvider() != null) {
			ParameterExpression<ExternalProvider> p = cb.parameter(
					ExternalProvider.class, "externalProvider");
			predicate = cb.and(predicate, cb.equal(
					root.<ExternalProvider> get("externalProvider"), p));
		}

		if (material.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("name")), p));
		}

		if (material.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("code"), p));
		}

		if (material.getExtCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"extCode");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("extCode"), p));
		}

		if (material.getModel() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "model");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("model")), p));
		}

		return predicate;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			Material entity = em.find(Material.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Material", e);
			throw new EJBException("ERROR: unable to delete Material "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#find(long)
	 */
	@Override
	public Material find(long Id) throws EJBException {
		try {
			return em.find(Material.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Material", e);
			throw new EJBException("ERROR: finding Material "
					+ e.getCause().getMessage());
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
	public List<Material> find(Material entity) throws EJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Material> cQuery = cb.createQuery(Material.class);
			Root<Material> root = cQuery.from(Material.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("code")));
			Predicate criteria = buildFilters(entity, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<Material> q = em.createQuery(cQuery);

			if (entity.getType() != null)
				q.setParameter("type", entity.getType());

			if (entity.getDescription() != null)
				q.setParameter("description", "%" + entity.getDescription()
						+ "%");

			if (entity.getExternalProvider() != null)
				q.setParameter("externalProvider", entity.getExternalProvider());

			if (entity.getName() != null)
				q.setParameter("name", "%" + entity.getName() + "%");

			if (entity.getCode() != null)
				q.setParameter("code", entity.getCode());

			if (entity.getExtCode() != null)
				q.setParameter("extCode", entity.getExtCode());

			if (entity.getModel() != null)
				q.setParameter("model", "%" + entity.getModel() + "%");

			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los materials por materials", e);
			throw new EJBException(
					"Error obteniendo los materials por materials "
							+ e.getCause().getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#getAll()
	 */
	@Override
	public List<Material> getAll() throws EJBException {
		try {
			return em.createNamedQuery("Material.getAll", Material.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Materials", ex);
			throw new EJBException("Error obteniendo todas las Materials"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#getAll()
	 */
	@Override
	public List<Material> getAll(int offset, int size) throws EJBException {
		try {
			return em.createNamedQuery("Material.getAll", Material.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Materials", ex);
			throw new EJBException("Error obteniendo todos los Materials"
					+ ex.getCause().getMessage());
		}
	}

	@Override
	public List<Material> getAllUtilities() throws EJBException {
		try {
			return em
					.createNamedQuery("Material.getByType", Material.class)
					.setParameter("materialTypeId", MaterialTypeEnum.UTILITARIO)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error retrieving all Materials who are Utilities", ex);
			throw new EJBException(
					"Error obteniendo todos los Materials que son utilitarios"
							+ ex.getCause().getMessage());
		}

	}

	@Override
	public List<Material> getAllUtilities(int offset, int size)
			throws EJBException {
		try {
			return em
					.createNamedQuery("Material.getByType", Material.class)
					.setParameter("materialTypeId", MaterialTypeEnum.UTILITARIO)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error retrieving all Materials who are Utilities", ex);
			throw new EJBException(
					"Error obteniendo todos los Materials que son utilitarios"
							+ ex.getCause().getMessage());
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
	public Material save(Material material) throws EJBException {
		try {
			em.persist(material);
			em.flush();
			return em.find(Material.class, material.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Material ", e);
			throw new EJBException("ERROR: saving Material "
					+ e.getCause().getMessage());
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
	public Material update(Material material) throws EJBException {
		try {
			Material res = em.merge(material);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Material ", e);
			throw new EJBException("ERROR: no se puede eliminar el Material "
					+ e.getCause().getMessage());
		}
	}

}
