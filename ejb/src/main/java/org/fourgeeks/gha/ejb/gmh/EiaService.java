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

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.EiaService")
public class EiaService implements EiaServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaService.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#buildFilters(org.fourgeeks
	 * .gha.domain.gmh.Eia, javax.persistence.criteria.CriteriaBuilder,
	 * javax.persistence.criteria.Root)
	 */
	@Override
	public Predicate buildFilters(Eia entity, CriteriaBuilder cb, Root<Eia> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getResponsibleRole() != null) {
			ParameterExpression<RoleBase> p = cb.parameter(RoleBase.class,
					"baseRole");
			criteria = cb.and(criteria,
					cb.equal(root.<RoleBase> get("responsibleRole"), p));
		}
		if (entity.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			criteria = cb.and(criteria, cb.equal(root.<String> get("code"), p));
		}
		if (entity.getEiaType() != null) {
			ParameterExpression<EiaType> p = cb.parameter(EiaType.class,
					"eiaType");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaType> get("eiaType"), p));
		}
		if (entity.getObu() != null) {
			ParameterExpression<Obu> p = cb.parameter(Obu.class, "obu");
			criteria = cb.and(criteria, cb.equal(root.<Obu> get("obu"), p));
		}
		if (entity.getSerialNumber() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"serialNumber");
			criteria = cb.and(criteria,
					cb.equal(root.<String> get("serialNumber"), p));
		}
		if (entity.getState() != null) {
			ParameterExpression<EiaStateEnum> p = cb.parameter(
					EiaStateEnum.class, "state");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaStateEnum> get("state"), p));
		}
		if (entity.getProvider() != null) {
			ParameterExpression<ExternalProvider> p = cb.parameter(
					ExternalProvider.class, "provider");
			criteria = cb.and(criteria,
					cb.equal(root.<ExternalProvider> get("provider"), p));
		}

		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id) throws EJBException {
		try {
			Eia entity = em.find(Eia.class, Id);
			em.remove(entity);
			return true;

		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete eia=" + Eia.class.getName()
							+ " with id=" + Long.toString(Id), e);
			throw new EJBException("ERROR: eliminando un eia por id "
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
	public List<Eia> find(Eia entity) throws EJBException {
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
				if (entity.getProvider() != null) {
					q.setParameter("provider", entity.getProvider());
				}
			}
			return q.getResultList();
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los Eia utilizando otro Eia", e);
			throw new EJBException(
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
	public List<Eia> findByEiaType(EiaType eiaType) throws EJBException {
		try {
			return em.createNamedQuery("Eia.findByEiaType", Eia.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding eia by eiatype", e);
			throw new EJBException("Error buscando eia por eiatype "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(long)
	 */
	@Override
	public Eia find(long Id) throws EJBException {
		try {
			return em.find(Eia.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding eia by id", e);
			throw new EJBException("Error buscando eia por id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#getAll()
	 */
	@Override
	public List<Eia> getAll() throws EJBException {
		try {
			return em.createNamedQuery("Eia.getAll", Eia.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eias", ex);
			throw new EJBException("Error obteniendo todos los eias"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#getAll(int, int)
	 */
	@Override
	public List<Eia> getAll(int offset, int size) throws EJBException {
		try {
			return em.createNamedQuery("Eia.getAll", Eia.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eias", ex);
			throw new EJBException("Error obteniendo todos los eias"
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
	public Eia save(Eia eia) throws EJBException {
		try {
			em.persist(eia);
			em.flush();
			return em.find(Eia.class, eia.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving eia ", e);
			throw new EJBException("ERROR: guardando eia "
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
	public Eia update(Eia eia) throws EJBException {
		try {
			Eia res = em.merge(eia);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eia ", e);
			throw new EJBException("ERROR: no se puede actualizar el eia "
					+ e.getCause().getMessage());

		}

	}
}
