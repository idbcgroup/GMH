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

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.maintenanceProtocolService")
public class MaintenanceProtocolService implements
		MaintenanceProtocolServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaintenanceProtocolService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			MaintenanceProtocol entity = em.find(MaintenanceProtocol.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error eliminando MaintenanceProtocol por id "
							+ e.getCause().getMessage());
			throw new GHAEJBException(
					"Error eliminando MaintenanceProtocol por id "
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#
	 * findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public List<MaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan) throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceProtocol.findByMaintenancePlan",
							MaintenanceProtocol.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error buscando MaintenanceProtocol por MaintenanceProtocol"
							+ e.getCause().getMessage());
			throw new GHAEJBException(
					"Error buscando MaintenanceProtocol por MaintenanceProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#
	 * findByMaintenancePlan(org.fourgeeks.gha.domain.gmh.MaintenancePlan, int,
	 * int)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#
	 * findByEiaTypeMaintenancePlan
	 * (org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan, int, int)
	 */
	@Override
	public List<MaintenanceProtocol> findByMaintenancePlan(
			MaintenancePlan maintenancePlan, int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery(
							"MaintenanceProtocol.findByMaintenancePlan",
							MaintenanceProtocol.class)
					.setParameter("maintenancePlan", maintenancePlan)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error buscando MaintenanceProtocol por MaintenanceProtocol"
							+ e.getCause().getMessage());
			throw new GHAEJBException(
					"Error buscando MaintenanceProtocol por MaintenanceProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#find(org.fourgeeks
	 * .gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public List<MaintenanceProtocol> find(
			MaintenanceProtocol maintenanceProtocol) throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MaintenanceProtocol> cQuery = cb
					.createQuery(MaintenanceProtocol.class);
			Root<MaintenanceProtocol> root = cQuery
					.from(MaintenanceProtocol.class);

			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("name")));
			Predicate criteria = buildFilters(maintenanceProtocol, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<MaintenanceProtocol> q = em.createQuery(cQuery);

			if (maintenanceProtocol.getName() != null)
				q.setParameter("name", "%"
						+ maintenanceProtocol.getName().toLowerCase() + "%");
			if (maintenanceProtocol.getDescription() != null)
				q.setParameter("description", "%"
						+ maintenanceProtocol.getDescription().toLowerCase()
						+ "%");

			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los maintenancePlan por maintenancePlan",
					e);
			throw new GHAEJBException(
					"Error obteniendo los maintenancePlan por maintenancePlan "
							+ e.getCause().getMessage());
		}
	}

	/**
	 * @param maintenanceProtocol
	 * @param cb
	 * @param root
	 * @return
	 */
	private Predicate buildFilters(MaintenanceProtocol maintenanceProtocol,
			CriteriaBuilder cb, Root<MaintenanceProtocol> root) {
		Predicate predicate = cb.conjunction();
		if (maintenanceProtocol.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("name")), p));
		}
		if (maintenanceProtocol.getDescription() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"description");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("description")), p));
		}
		return predicate;
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public MaintenanceProtocol find(long Id) throws GHAEJBException {
		try {
			return em.find(MaintenanceProtocol.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding MaintenanceProtocol", e);
			throw new GHAEJBException("ERROR: finding MaintenanceProtocol "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#getAll()
	 */
	@Override
	public List<MaintenanceProtocol> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenanceProtocol.getAll",
					MaintenanceProtocol.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceProtocol", e);
			throw new GHAEJBException(
					"Error buscando todos los MaintenanceProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#getAll(int,
	 * int)
	 */
	@Override
	public List<MaintenanceProtocol> getAll(int offset, int size)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("MaintenanceProtocol.getAll",
							MaintenanceProtocol.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all MaintenanceProtocol", e);
			throw new GHAEJBException(
					"Error buscando todos los MaintenanceProtocol"
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public MaintenanceProtocol save(MaintenanceProtocol maintenanceProtocol)
			throws GHAEJBException {
		try {
			em.persist(maintenanceProtocol);
			em.flush();
			return em.find(MaintenanceProtocol.class,
					maintenanceProtocol.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenanceProtocol ", e);
			throw new GHAEJBException("ERROR: saving MaintenanceProtocol "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.MaintenanceProtocolServiceRemote#update(org
	 * .fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public MaintenanceProtocol update(MaintenanceProtocol maintenanceProtocol)
			throws GHAEJBException {
		try {
			MaintenanceProtocol res = em.merge(maintenanceProtocol);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update MaintenanceProtocol ", e);
			throw new GHAEJBException(
					"ERROR: no se puede actualizar el MaintenanceProtocol "
							+ e.getCause().getMessage());
		}
	}

}
