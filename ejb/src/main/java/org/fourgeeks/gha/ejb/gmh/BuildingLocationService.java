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

import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret, vivi.torresg
 * 
 */
@Stateless
public class BuildingLocationService extends GHAEJBExceptionImpl implements
		BuildingLocationServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(BuildingLocationService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.BuildingLocationServiceRemote#buildFilters(
	 * org.fourgeeks.gha.domain.gar.BuildingLocation,
	 * javax.persistence.criteria.CriteriaBuilder,
	 * javax.persistence.criteria.Root)
	 */
	@Override
	public Predicate buildFilters(BuildingLocation entity, CriteriaBuilder cb,
			Root<BuildingLocation> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			criteria = cb.and(criteria, cb.equal(root.<String> get("code"), p));
		}
		if (entity.getBpi() != null) {
			ParameterExpression<Bpi> p = cb.parameter(Bpi.class, "bpi");
			criteria = cb.and(criteria, cb.equal(root.<Bpi> get("bpi"), p));
		}
		if (entity.getLocationLevel() != null) {
			ParameterExpression<LocationLevelEnum> p = cb.parameter(
					LocationLevelEnum.class, "locationLevel");
			criteria = cb.and(criteria,
					cb.equal(root.<LocationLevelEnum> get("locationLevel"), p));
		}
		if (entity.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("name")), p));
		}
		if (entity.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("description")), p));
		}

		return criteria;
	}

	@Override
	public void delete(String id) throws GHAEJBException {
		try {
			BuildingLocation entity = em.find(BuildingLocation.class, id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete BuildingLocation",
					e);
			throw super.generateGHAEJBException("buildingLocation-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<BuildingLocation> find(BuildingLocation entity)
			throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<BuildingLocation> cQuery = cb
					.createQuery(BuildingLocation.class);
			Root<BuildingLocation> root = cQuery.from(BuildingLocation.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("name")));

			Predicate criteria = buildFilters(entity, cb, root);
			TypedQuery<BuildingLocation> q;
			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (entity.getCode() != null) {
					q.setParameter("code", entity.getCode());
				}
				if (entity.getBpi() != null) {
					q.setParameter("bpi", entity.getBpi());
				}
				if (entity.getLocationLevel() != null) {
					q.setParameter("locationLevel", entity.getLocationLevel());
				}
				if (entity.getName() != null) {
					q.setParameter("name", entity.getName());
				}
				if (entity.getDescription() != null) {
					q.setParameter("description", entity.getDescription());
				}
			}
			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding BuildingLocation by BuildingLocation", e);
			throw super.generateGHAEJBException(
					"buildingLocation-findByBuildingLocation-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public BuildingLocation find(String id) throws GHAEJBException {
		try {
			return em.find(BuildingLocation.class, id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding BuildingLocation", e);
			throw super.generateGHAEJBException("buildingLocation-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<BuildingLocation> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("BuildingLocation.getAll",
					BuildingLocation.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all BuildingLocation",
					ex);
			throw super.generateGHAEJBException("buildingLocation-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public BuildingLocation save(BuildingLocation entity)
			throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(BuildingLocation.class, entity.getCode());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving BuildingLocation " + entity.toString(), e);
			throw super.generateGHAEJBException("buildingLocation-save-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	@Override
	public BuildingLocation update(BuildingLocation entity)
			throws GHAEJBException {
		try {
			BuildingLocation res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update BuildingLocation "
					+ entity.toString(), e);
			throw super.generateGHAEJBException("buildingLocation-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

}
