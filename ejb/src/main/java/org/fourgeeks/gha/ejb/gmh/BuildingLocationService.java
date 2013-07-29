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
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.mix.Bpi;

/**
 * @author alacret
 * 
 */
@Stateless(name = "gmh.BuildingLocationService")
public class BuildingLocationService implements BuildingLocationServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(BuildingLocationService.class.getName());

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.BuildingLocationServiceRemote#buildFilters(org.fourgeeks.gha.domain.gar.BuildingLocation, javax.persistence.criteria.CriteriaBuilder, javax.persistence.criteria.Root)
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
			criteria = cb.and(criteria, cb.equal(root.<Bpi>get("bpi"), p));
		}
		if (entity.getLocationLevel() != null) {
			ParameterExpression<LocationLevelEnum> p = cb.parameter(LocationLevelEnum.class, "locationLevel");
			criteria = cb.and(criteria, cb.equal(root.<LocationLevelEnum>get("locationLevel"), p));
		}
		if (entity.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			criteria = cb.and(criteria, cb.like(cb.lower(root.<String>get("name")), p));
		}
		if (entity.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "description");
			criteria = cb.and(criteria, cb.like(cb.lower(root.<String>get("description")), p));
		}
		
		return criteria;
	}

	@Override
	public void delete(long Id) throws EJBException {
		try {
			BuildingLocation entity = em.find(BuildingLocation.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete BuildingLocation",
					e);
			throw new EJBException("ERROR: unable to delete BuildingLocation "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<BuildingLocation> find(BuildingLocation entity)
			throws EJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<BuildingLocation> cQuery = cb.createQuery(BuildingLocation.class);
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
					q.setParameter("locationLevel",
							entity.getLocationLevel());
				}
				if (entity.getName() != null) {
					q.setParameter("name",
							entity.getName());
				}
				if (entity.getDescription() != null) {
					q.setParameter("description", entity.getDescription());
				}
			}
			return q.getResultList();
			
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding BuildingLocation by BuildingLocation", e);
			throw new EJBException(
					"Error finding BuildingLocation by BuildingLocation "
							+ e.getCause().getMessage());
		}
	}

	@Override
	public BuildingLocation find(long Id) throws EJBException {
		try {
			return em.find(BuildingLocation.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding BuildingLocation", e);
			throw new EJBException("ERROR: finding BuildingLocation "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public List<BuildingLocation> getAll() throws EJBException {
		try {
			return em.createNamedQuery("BuildingLocation.getAll",
					BuildingLocation.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all BuildingLocation",
					ex);
			throw new EJBException("Error obteniendo todas las brands"
					+ ex.getCause().getMessage());
		}
	}

	@Override
	public BuildingLocation save(BuildingLocation entity) throws EJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(BuildingLocation.class, entity.getCode());
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: saving BuildingLocation " + entity.toString(), e);
			throw new EJBException("ERROR: saving BuildingLocation "
					+ e.getCause().getMessage());
		}

	}

	@Override
	public BuildingLocation update(BuildingLocation entity) throws EJBException {
		try {
			BuildingLocation res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update BuildingLocation "
					+ entity.toString(), e);
			throw new EJBException(
					"ERROR: no se puede eliminar el BuildingLocation "
							+ e.getCause().getMessage());
		}
	}

}
