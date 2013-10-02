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

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.maintenancePlanService")
public class MaintenancePlanService implements MaintenancePlanServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(MaintenancePlanService.class
			.getName());

	/**
	 * @param maintenancePlan
	 * @param cb
	 * @param root
	 * @return
	 */
	private Predicate buildFilters(MaintenancePlan maintenancePlan,
			CriteriaBuilder cb, Root<MaintenancePlan> root) {
		Predicate predicate = cb.conjunction();
		if (maintenancePlan.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"name");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("name")), p));
		}
		if (maintenancePlan.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("description")), p));
		}
		if(maintenancePlan.getPot() != null){
			ParameterExpression<TimePeriodEnum> p = cb.parameter(TimePeriodEnum.class,
					"pot");
			predicate = cb.and(predicate, cb.equal(root.<TimePeriodEnum>get("pot"), p));
		}
		if(maintenancePlan.getFrequency() > 0){
			ParameterExpression<Integer> p = cb.parameter(Integer.class, "frequency");
			predicate = cb.and(predicate, cb.equal(root.<Integer>get("frequency"), p));
		}
		
		return predicate;
	}
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			MaintenancePlan entity = em.find(MaintenancePlan.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete MaintenancePlan", e);
			throw new GHAEJBException("Error eliminando MaintenancePlan por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<MaintenancePlan> findByEiaType(EiaType eiaType) throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenancePlan.findByEiaType", MaintenancePlan.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenancePlan", e);
			throw new GHAEJBException("Error buscando MaintenancePlan por EiaType"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<MaintenancePlan> findByEiaType(EiaType eiaType, int offset, int size)
			throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenancePlan.findByEiaType", MaintenancePlan.class)
					.setParameter("eiaType", eiaType).setFirstResult(offset).setMaxResults(size)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding by MaintenancePlan", e);
			throw new GHAEJBException("Error buscando MaintenancePlan por EiaType"
					+ e.getCause().getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenancePlan> find(MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MaintenancePlan> cQuery = cb.createQuery(MaintenancePlan.class);
			Root<MaintenancePlan> root = cQuery.from(MaintenancePlan.class);

			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("name")));
			Predicate criteria = buildFilters(maintenancePlan, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<MaintenancePlan> q = em.createQuery(cQuery);

			if (maintenancePlan.getName() != null)
				q.setParameter("name", "%" + maintenancePlan.getName().toLowerCase() + "%");
			if(maintenancePlan.getDescription() != null)
				q.setParameter("description", "%" + maintenancePlan.getDescription().toLowerCase() + "%");
			if(maintenancePlan.getPot() != null)
				q.setParameter("pot", maintenancePlan.getPot());
			if(maintenancePlan.getFrequency() > 0)
				q.setParameter("frequency", maintenancePlan.getFrequency());


			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los maintenancePlan por maintenancePlan", e);
			throw new GHAEJBException(
					"Error obteniendo los maintenancePlan por maintenancePlan "
							+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#find(long)
	 */
	@Override
	public MaintenancePlan find(long Id) throws GHAEJBException {
		try {
			return em.find(MaintenancePlan.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaintenancePlan", e);
			throw new GHAEJBException("ERROR: finding MaintenancePlan "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#getAll()
	 */
	@Override
	public List<MaintenancePlan> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenancePlan.getAll", MaintenancePlan.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenancePlan", e);
			throw new GHAEJBException("Error buscando todos los MaintenancePlan"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#getAll(int, int)
	 */
	@Override
	public List<MaintenancePlan> getAll(int offset, int size) throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenancePlan.getAll", MaintenancePlan.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenancePlan", e);
			throw new GHAEJBException("Error buscando todos los MaintenancePlan"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#save(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenancePlan save(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		try {
			em.persist(maintenancePlan);
			em.flush();
			return em.find(MaintenancePlan.class, maintenancePlan.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenancePlan ", e);
			throw new GHAEJBException("ERROR: saving MaintenancePlan "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenancePlanServiceRemote#update(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public MaintenancePlan update(MaintenancePlan maintenancePlan)
			throws GHAEJBException {
		try {
			MaintenancePlan res = em.merge(maintenancePlan);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update MaintenancePlan ", e);
			throw new GHAEJBException("ERROR: no se puede actualizar el MaintenancePlan "
					+ e.getCause().getMessage());
		}
	}
}
