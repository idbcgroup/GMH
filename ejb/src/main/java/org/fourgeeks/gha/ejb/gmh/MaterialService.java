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

import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;

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

	private static Predicate buildFilters(Material entity, CriteriaBuilder cb,
			Root<Material> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			criteria = cb.and(criteria, cb.equal(root.<String> get("code"), p));
		}

		if (entity.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("description")), p));
		}

		if (entity.getModel() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "model");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("model")), p));
		}

		if (entity.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("name")), p));
		}

		if (entity.getType() != null) {
			ParameterExpression<EiaTypeEnum> p = cb.parameter(
					EiaTypeEnum.class, "type");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaTypeEnum> get("type"), p));
		}

		return criteria;

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

			cQuery.orderBy(cb.asc(root.<String> get("name")));

			Predicate criteria = buildFilters(entity, cb, root);
			TypedQuery<EiaType> q;
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los eiaTypes por eiatype", e);
			throw new EJBException("Error obteniendo los eiaTypes por eiatype "
					+ e.getCause().getMessage());
		}

		try {
			return em.createNamedQuery("Material.findByName", Material.class)
					.setParameter("name", entity.getName()).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by Material Material", e);
			throw new EJBException("Error buscando Material por Material "
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaterialServiceRemote#save(org.fourgeeks.gha
	 * .domain .gmh.Material)
	 */
	@Override
	public Material save(Material Material) throws EJBException {
		try {
			em.persist(Material);
			em.flush();
			return em.find(Material.class, Material.getId());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving Material " + Material.toString(), e);
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
	public Material update(Material Material) throws EJBException {
		try {
			Material res = em.merge(Material);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Material "
					+ Material.toString(), e);
			throw new EJBException("ERROR: no se puede eliminar el Material "
					+ e.getCause().getMessage());
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

}
