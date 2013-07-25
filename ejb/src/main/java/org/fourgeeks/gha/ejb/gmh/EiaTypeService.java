/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.EiaTypeService")
public class EiaTypeService implements EiaTypeServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaTypeService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#buildFilters(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public Predicate buildFilters(EiaType entity, CriteriaBuilder cb,
			Root<EiaType> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getBrand() != null) {
			ParameterExpression<Brand> p = cb.parameter(Brand.class, "brand");
			criteria = cb
					.and(criteria, cb.equal(root.<Brand> get("brand"), p));
		}

		if (entity.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			criteria = cb
					.and(criteria, cb.equal(root.<String> get("code"), p));
		}

		if (entity.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("description")), p));
		}

		if (entity.getEiaUmdns() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"eiaumdns");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("eiaumdns")), p));
		}

		if (entity.getManufacturer() != null) {
			ParameterExpression<Manufacturer> p = cb.parameter(
					Manufacturer.class, "manufacturer");
			criteria = cb.and(criteria,
					cb.equal(root.<Manufacturer> get("manufacturer"), p));
		}

		if (entity.getMobility() != null) {
			ParameterExpression<EiaMobilityEnum> p = cb.parameter(
					EiaMobilityEnum.class, "mobility");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaMobilityEnum> get("mobility"), p));
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

		if (entity.getSubtype() != null) {
			ParameterExpression<EiaSubTypeEnum> p = cb.parameter(
					EiaSubTypeEnum.class, "subtype");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaSubTypeEnum> get("subtype"), p));
		}

		if (entity.getType() != null) {
			ParameterExpression<EiaTypeEnum> p = cb.parameter(
					EiaTypeEnum.class, "type");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaTypeEnum> get("type"), p));
		}

		if (entity.getUseDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"usedescription");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("usedescription")), p));
		}

		return criteria;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#deleteEiaType(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			EiaType entity = em.find(EiaType.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatype", e);
			throw new EJBException("Error eliminando EiaType por id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#find(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType) throws EJBException {

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<EiaType> c = cb.createQuery(EiaType.class);
			Root<EiaType> eType = c.from(EiaType.class);
			c.select(eType);

			Predicate criteria = buildFilters(eiaType, cb, eType);
			c.where(criteria);

			TypedQuery<EiaType> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(c);
			} else {
				c.where(criteria);
				q = em.createQuery(c);

				if (eiaType.getBrand() != null) {
					q.setParameter("brand", eiaType.getBrand());
				}

				if (eiaType.getCode() != null) {
					q.setParameter("code", eiaType.getCode());
				}

				if (eiaType.getDescription() != null) {
					q.setParameter("description", "%"
							+ eiaType.getDescription().toLowerCase() + "%");
				}

				if (eiaType.getEiaUmdns() != null) {
					q.setParameter("eiaumdns", "%"
							+ eiaType.getEiaUmdns().toLowerCase() + "%");
				}

				if (eiaType.getManufacturer() != null) {
					q.setParameter("manufacturer", eiaType.getManufacturer());
				}

				if (eiaType.getMobility() != null) {
					q.setParameter("mobility", eiaType.getMobility());
				}

				if (eiaType.getModel() != null) {
					q.setParameter("model", "%"
							+ eiaType.getModel().toLowerCase() + "%");
				}

				if (eiaType.getName() != null) {
					q.setParameter("name", "%"
							+ eiaType.getName().toLowerCase() + "%");
				}

				if (eiaType.getSubtype() != null) {
					q.setParameter("subtype", eiaType.getSubtype());
				}

				if (eiaType.getType() != null) {
					q.setParameter("type", eiaType.getType());
				}

				if (eiaType.getUseDescription() != null) {
					q.setParameter("usedescription", "%"
							+ eiaType.getUseDescription().toLowerCase() + "%");
				}
			}

			return q.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo buscando los eiaTypes por eiatype", e);
			throw new EJBException(
					"Error obteniendo buscando los eiaTypes por eiatype "
							+ e.getCause().getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#find(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public List<EiaType> find(EiaType eiaType, int offset, int size)
			throws EJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<EiaType> c = cb.createQuery(EiaType.class);
			Root<EiaType> eType = c.from(EiaType.class);
			c.select(eType);

			Predicate criteria = buildFilters(eiaType, cb, eType);
			c.where(criteria);

			TypedQuery<EiaType> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(c);
			} else {
				c.where(criteria);
				q = em.createQuery(c);

				if (eiaType.getBrand() != null) {
					q.setParameter("brand", eiaType.getBrand());
				}

				if (eiaType.getCode() != null) {
					q.setParameter("code", eiaType.getCode());
				}

				if (eiaType.getDescription() != null) {
					q.setParameter("description", "%"
							+ eiaType.getDescription().toLowerCase() + "%");
				}

				if (eiaType.getEiaUmdns() != null) {
					q.setParameter("eiaumdns", "%"
							+ eiaType.getEiaUmdns().toLowerCase() + "%");
				}

				if (eiaType.getManufacturer() != null) {
					q.setParameter("manufacturer", eiaType.getManufacturer());
				}

				if (eiaType.getMobility() != null) {
					q.setParameter("mobility", eiaType.getMobility());
				}

				if (eiaType.getModel() != null) {
					q.setParameter("model", "%"
							+ eiaType.getModel().toLowerCase() + "%");
				}

				if (eiaType.getName() != null) {
					q.setParameter("name", "%"
							+ eiaType.getName().toLowerCase() + "%");
				}

				if (eiaType.getSubtype() != null) {
					q.setParameter("subtype", eiaType.getSubtype());
				}

				if (eiaType.getType() != null) {
					q.setParameter("type", eiaType.getType());
				}

				if (eiaType.getUseDescription() != null) {
					q.setParameter("usedescription", "%"
							+ eiaType.getUseDescription().toLowerCase() + "%");
				}
			}
			
			q.setFirstResult(offset);
			q.setMaxResults(size);
			
			return q.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo buscando los eiaTypes por eiatype", e);
			throw new EJBException(
					"Error obteniendo buscando los eiaTypes por eiatype "
							+ e.getCause().getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getEiaType(long)
	 */
	@Override
	public EiaType find(long Id) throws EJBException {
		try {
			return em.find(EiaType.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando Eiatype por Id ", e);
			throw new EJBException("Error buscando Eiatype por Id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAllEiaType()
	 */
	@Override
	public List<EiaType> getAll() throws EJBException {
		List<EiaType> res = null;
		try {
			res = em.createNamedQuery("EiaType.getAll", EiaType.class)
					.getResultList();
		} catch (NoResultException e) {
			logger.log(Level.INFO, "No results", e);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiatypes", ex);
			throw new EJBException("Error obteniendo todos los eiaTypes");
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAll(long, long)
	 */
	@Override
	public List<EiaType> getAll(int offset, int size) throws EJBException {
		List<EiaType> res = null;
		try {
			res = em.createNamedQuery("EiaType.getAll", EiaType.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (NoResultException e) {
			logger.log(Level.INFO, "Get All: no results", e);
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EitaTypes", ex);
			throw new EJBException(
					"Error obteniendo todos los eiaTypes paginado");
		}

		return res;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeService#saveEiaType(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public EiaType save(EiaType eiaType) throws EJBException {
		try {
			em.persist(eiaType);
			em.flush();
			return em.find(EiaType.class, eiaType.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiatype", e);
			throw new EJBException("Error guardando EiaType: "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeService#updateEiaType(org.fourgeeks.
	 * gha.domain.gmh.EiaType)
	 */
	@Override
	public EiaType update(EiaType eiaType) throws EJBException {
		try {
			EiaType res = em.merge(eiaType);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiatype", e);
			throw new EJBException("Error actualizando EiaType "
					+ e.getCause().getMessage());
		}
	}

}
