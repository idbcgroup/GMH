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

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
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

	private static Predicate buildFilters(EiaType entity, CriteriaBuilder cb,
			Root<EiaType> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getBrand() != null) {
			ParameterExpression<Brand> p = cb.parameter(Brand.class, "brand");
			criteria = cb.and(criteria, cb.equal(root.<Brand> get("brand"), p));
		}

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

		if (entity.getEiaUmdns() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"eiaumdns");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("eiaUmdns")), p));
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
					EiaTypeEnum.class, "etype");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaTypeEnum> get("type"), p));
		}

		if (entity.getUseDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"usedescription");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("useDescription")), p));
		}

		return criteria;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#deleteEiaType(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			EiaType entity = em.find(EiaType.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eiatype", e);
			throw new GHAEJBException("Error eliminando EiaType por id "
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
	public List<EiaType> find(EiaType entity) throws GHAEJBException {

		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<EiaType> cQuery = cb.createQuery(EiaType.class);
			Root<EiaType> root = cQuery.from(EiaType.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("name")));

			Predicate criteria = buildFilters(entity, cb, root);
			TypedQuery<EiaType> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (entity.getBrand() != null) {
					q.setParameter("brand", entity.getBrand());
				}

				if (entity.getCode() != null) {
					q.setParameter("code", entity.getCode());
				}

				if (entity.getDescription() != null) {
					q.setParameter("description", "%"
							+ entity.getDescription().toLowerCase() + "%");
				}

				if (entity.getEiaUmdns() != null) {
					q.setParameter("eiaumdns", "%"
							+ entity.getEiaUmdns().toLowerCase() + "%");
				}

				if (entity.getMobility() != null) {
					q.setParameter("mobility", entity.getMobility());
				}

				if (entity.getModel() != null) {
					q.setParameter("model", "%"
							+ entity.getModel().toLowerCase() + "%");
				}

				if (entity.getName() != null) {
					q.setParameter("name", "%" + entity.getName().toLowerCase()
							+ "%");
				}

				if (entity.getSubtype() != null) {
					q.setParameter("subtype", entity.getSubtype());
				}

				if (entity.getType() != null) {
					q.setParameter("etype", entity.getType());
				}

				if (entity.getUseDescription() != null) {
					q.setParameter("usedescription", "%"
							+ entity.getUseDescription().toLowerCase() + "%");
				}
			}
			return q.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los eiaTypes por eiatype", e);
			throw new GHAEJBException("Error obteniendo los eiaTypes por eiatype "
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
			throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<EiaType> cQuery = cb.createQuery(EiaType.class);
			Root<EiaType> root = cQuery.from(EiaType.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("name")));

			Predicate criteria = buildFilters(eiaType, cb, root);
			cQuery.where(criteria);

			TypedQuery<EiaType> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

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
					q.setParameter("etype", eiaType.getType());
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
			throw new GHAEJBException(
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
	public EiaType find(String code) throws GHAEJBException {
		try {
			return em.find(EiaType.class, code);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando Eiatype por Id ", e);
			throw new GHAEJBException("Error buscando Eiatype por Id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAllEiaType()
	 */
	@Override
	public List<EiaType> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaType.getAll", EiaType.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eiatypes", ex);
			throw new GHAEJBException("Error obteniendo todos los eiaTypes");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeService#getAll(long, long)
	 */
	@Override
	public List<EiaType> getAll(int offset, int size) throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaType.getAll", EiaType.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EitaTypes", ex);
			throw new GHAEJBException(
					"Error obteniendo todos los eiaTypes paginado");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypeService#saveEiaType(org.fourgeeks.gha
	 * .domain.gmh.EiaType)
	 */
	@Override
	public EiaType save(EiaType eiaType) throws GHAEJBException {
		try {
			Brand brand = eiaType.getBrand();
			
			if(brand != null && brand.getId() <= 0){
				Manufacturer manufacturer = brand.getManufacturer();
				if(manufacturer != null && manufacturer.getId() <= 0){
					em.persist(manufacturer);
				}
				em.persist(brand);
			}
			em.persist(eiaType);
			em.flush();
			return em.find(EiaType.class, eiaType.getCode());
			
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eiatype", e);
			throw new GHAEJBException("Error guardando EiaType: "
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
	public EiaType update(EiaType eiaType) throws GHAEJBException {
		try {
			EiaType res = em.merge(eiaType);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiatype", e);
			throw new GHAEJBException("Error actualizando EiaType "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote#findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<EiaType> findByMaintenancePlan(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		try {
			return em.createNamedQuery("EiaType.findByMaintenancePlan", EiaType.class)
					.setParameter("maintenancePlan", maintenancePlan).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retriving all EitaTypes by maintenancePlan", ex);
			throw new GHAEJBException(
					"Error obteniendo todos los eiaTypes por plan de mantenimiento");
		}
	}

}
