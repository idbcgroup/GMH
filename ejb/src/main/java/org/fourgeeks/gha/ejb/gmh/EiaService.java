/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
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

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
//import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "gmh.EiaService")
public class EiaService implements EiaServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaService.class
			.getName());

	private final static Predicate buildFilters(Eia entity, CriteriaBuilder cb,
			Root<Eia> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getResponsibleRole() != null) {
			System.out.println("1");
			ParameterExpression<Role> p = cb.parameter(Role.class, "baseRole");
			criteria = cb.and(criteria,
					cb.equal(root.<Role> get("responsibleRole"), p));
		}
		if (entity.getCode() != null) {
			System.out.println("2");
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			criteria = cb.and(criteria, cb.equal(root.<String> get("code"), p));
		}
		if (entity.getEiaType() != null) {
			System.out.println("3");
			ParameterExpression<EiaType> p = cb.parameter(EiaType.class,
					"eiaType");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaType> get("eiaType"), p));
		}
		if (entity.getObu() != null) {
			System.out.println("4");
			ParameterExpression<Obu> p = cb.parameter(Obu.class, "obu");
			criteria = cb.and(criteria, cb.equal(root.<Obu> get("obu"), p));
		}
		if (entity.getSerialNumber() != null) {
			System.out.println("5");
			ParameterExpression<String> p = cb.parameter(String.class,
					"serialNumber");
			criteria = cb.and(criteria,
					cb.equal(root.<String> get("serialNumber"), p));
		}
		if (entity.getState() != null) {
			System.out.println("6");
			ParameterExpression<EiaStateEnum> p = cb.parameter(
					EiaStateEnum.class, "state");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaStateEnum> get("state"), p));
		}
		if (entity.getActualCost() != null) {
			System.out.println("7");
			ParameterExpression<BigDecimal> p = cb.parameter(BigDecimal.class,
					"actualCost");
			criteria = cb.and(criteria,
					cb.equal(root.<BigDecimal> get("actualCost"), p));
		}
		if (entity.getWorkingArea() != null) {
			System.out.println("8");
			ParameterExpression<WorkingArea> p = cb.parameter(
					WorkingArea.class, "workingArea");
			criteria = cb.and(criteria,
					cb.equal(root.<WorkingArea> get("workingArea"), p));
		}
		if (entity.getFacility() != null) {
			System.out.println("9");
			ParameterExpression<Facility> p = cb.parameter(Facility.class,
					"facility");
			criteria = cb.and(criteria,
					cb.equal(root.<Facility> get("facility"), p));
		}

		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id) throws GHAEJBException {
		try {
			Eia entity = em.find(Eia.class, Id);
			em.remove(entity);
			return true;

		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete eia=" + Eia.class.getName()
							+ " with id=" + Long.toString(Id), e);
			throw new GHAEJBException("ERROR: eliminando un eia por id "
					+ e.getCause().getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(org.fourgeeks.gha.domain
	 * .gmh.Eia)
	 */
	@Override
	public List<Eia> find(Eia entity) throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Eia> cQuery = cb.createQuery(Eia.class);
			Root<Eia> root = cQuery.from(Eia.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("id")));
			Predicate criteria = buildFilters(entity, cb, root);
			TypedQuery<Eia> q;
			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (entity.getResponsibleRole() != null) {
					q.setParameter("baseRole", entity.getResponsibleRole());
				}
				if (entity.getCode() != null) {
					q.setParameter("code", entity.getCode());
				}
				if (entity.getEiaType() != null) {
					q.setParameter("eiaType", entity.getEiaType());
				}
				if (entity.getObu() != null) {
					q.setParameter("obu", entity.getObu());
				}
				if (entity.getSerialNumber() != null) {
					q.setParameter("serialNumber", entity.getSerialNumber());
				}
				if (entity.getState() != null) {
					q.setParameter("state", entity.getState());
				}
				// if (entity.getProvider() != null) {
				// q.setParameter("provider", entity.getProvider());
				// }
				if (entity.getActualCost() != null) {
					q.setParameter("actualCost", entity.getActualCost());
				}
				if (entity.getWorkingArea() != null) {
					q.setParameter("workingArea", entity.getWorkingArea());
				}
				if (entity.getFacility() != null) {
					q.setParameter("facility", entity.getFacility());
				}
			}
			return q.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los Eia utilizando otro Eia", e);
			throw new GHAEJBException(
					"Error obteniendo los Eia utilizando otro Eia "
							+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(org.fourgeeks.gha.domain
	 * .gmh.EiaType)
	 */
	@Override
	public List<Eia> findByEiaType(EiaType eiaType) throws GHAEJBException {
		try {
			return em.createNamedQuery("Eia.findByEiaType", Eia.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding eia by eiatype", e);
			throw new GHAEJBException("Error buscando eia por eiatype "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(long)
	 */
	@Override
	public Eia find(long Id) throws GHAEJBException {
		try {
			return em.find(Eia.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding eia by id", e);
			throw new GHAEJBException("Error buscando eia por id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#getAll()
	 */
	@Override
	public List<Eia> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Eia.getAll", Eia.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eias", ex);
			throw new GHAEJBException("Error obteniendo todos los eias"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#getAll(int, int)
	 */
	@Override
	public List<Eia> getAll(int offset, int size) throws GHAEJBException {
		try {
			return em.createNamedQuery("Eia.getAll", Eia.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eias", ex);
			throw new GHAEJBException("Error obteniendo todos los eias"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#save(org.fourgeeks.gha.domain
	 * .gmh.Eia)
	 */
	@Override
	public Eia save(Eia eia) throws GHAEJBException {
		try {
			em.persist(eia);
			em.flush();
			return em.find(Eia.class, eia.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eia ", e);
			throw new GHAEJBException("ERROR: guardando eia "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#update(org.fourgeeks.gha.domain
	 * .gmh.Eia)
	 */
	@Override
	public Eia update(Eia eia) throws GHAEJBException {
		try {
			Eia res = em.merge(eia);
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eia ", e);
			throw new GHAEJBException("ERROR: no se puede actualizar el eia "
					+ e.getCause().getMessage());

		}

	}
}
