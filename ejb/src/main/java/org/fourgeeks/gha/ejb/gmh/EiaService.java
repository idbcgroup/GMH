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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
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
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "gmh.EiaService")
public class EiaService extends GHAEJBExceptionImpl implements EiaServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(EiaService.class
			.getName());

	private final static Predicate buildFilters(Eia entity, CriteriaBuilder cb,
			Root<Eia> root, Join<Eia, Obu> obuJoin) {
		Predicate predicate = cb.conjunction();
		if (entity.getResponsibleRole() != null) {
			// logger.log(Level.INFO, "responsible role");
			ParameterExpression<Role> p = cb.parameter(Role.class, "baseRole");
			predicate = cb.and(predicate,
					cb.equal(root.<Role> get("responsibleRole"), p));
		}
		if (entity.getCode() != null) {
			// logger.log(Level.INFO, "code");
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("code"), p));
		}
		if (entity.getEiaType() != null) {
			// logger.log(Level.INFO, "eiatype");
			ParameterExpression<EiaType> p = cb.parameter(EiaType.class,
					"eiaType");
			predicate = cb.and(predicate,
					cb.equal(root.<EiaType> get("eiaType"), p));
		}
		if (entity.getObu() != null) {
			final Obu obu = entity.getObu();
			if (obu.getId() > 0) {
				// logger.log(Level.INFO, "obu");
				ParameterExpression<Long> p = cb.parameter(Long.class, "obuId");
				Path<Long> obuId = obuJoin.<Long> get("id");
				predicate = cb.and(predicate, cb.equal(obuId, p));
			}
			if (obu.getBpi() != null) {
				// logger.log(Level.INFO, "obu.bpi");
				ParameterExpression<Bpi> p = cb.parameter(Bpi.class, "obuBpi");
				Path<Bpi> obuBpi = obuJoin.<Bpi> get("bpi");
				predicate = cb.and(predicate, cb.equal(obuBpi, p));
			}
		}
		if (entity.getSerialNumber() != null) {
			// logger.log(Level.INFO, "serial");
			ParameterExpression<String> p = cb.parameter(String.class,
					"serialNumber");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("serialNumber"), p));
		}
		if (entity.getState() != null) {
			// logger.log(Level.INFO, "state");
			ParameterExpression<EiaStateEnum> p = cb.parameter(
					EiaStateEnum.class, "state");
			predicate = cb.and(predicate,
					cb.equal(root.<EiaStateEnum> get("state"), p));
		}
		if (entity.getActualCost() != null) {
			// logger.log(Level.INFO, "actualCost");
			ParameterExpression<BigDecimal> p = cb.parameter(BigDecimal.class,
					"actualCost");
			predicate = cb.and(predicate,
					cb.equal(root.<BigDecimal> get("actualCost"), p));
		}
		if (entity.getWorkingArea() != null) {
			// logger.log(Level.INFO, "workingarea");
			ParameterExpression<WorkingArea> p = cb.parameter(
					WorkingArea.class, "workingArea");
			predicate = cb.and(predicate,
					cb.equal(root.<WorkingArea> get("workingArea"), p));
		}
		if (entity.getFacility() != null) {
			// logger.log(Level.INFO, "facility");
			ParameterExpression<Facility> p = cb.parameter(Facility.class,
					"facility");
			predicate = cb.and(predicate,
					cb.equal(root.<Facility> get("facility"), p));
		}
		return predicate;
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
			throw super.generateGHAEJBException("eia-delete-fail",
					RuntimeParameters.getLang(), em);
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
			Join<Eia, Obu> obuJoin = root.join("obu");
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("id")));
			Predicate criteria = buildFilters(entity, cb, root, obuJoin);
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
					final Obu obu = entity.getObu();
					if (obu.getId() > 0)
						q.setParameter("obuId", obu.getId());
					if (obu.getBpi() != null)
						q.setParameter("obuBpi", obu.getBpi());
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
			throw super.generateGHAEJBException("eia-findByEia-fail",
					RuntimeParameters.getLang(), em);
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
			throw super.generateGHAEJBException("eia-findByEiaType-fail",
					RuntimeParameters.getLang(), em);
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
			throw super.generateGHAEJBException("eia-find-fail",
					RuntimeParameters.getLang(), em);
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
			throw super.generateGHAEJBException("eia-getAll-fail",
					RuntimeParameters.getLang(), em);
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
			throw super.generateGHAEJBException("eia-getAll-fail",
					RuntimeParameters.getLang(), em);
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
			throw super.generateGHAEJBException("eia-save-fail",
					RuntimeParameters.getLang(), em);
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
			throw super.generateGHAEJBException("eia-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
