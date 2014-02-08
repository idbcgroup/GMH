/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class EiaService extends GHAEJBExceptionService implements
		EiaServiceRemote {
	@PersistenceContext
	EntityManager em;

	@EJB
	EiaTypeComponentServiceLocal eiaTypeComponentService;

	private final static Logger logger = Logger.getLogger(EiaService.class
			.getName());

	private final static Predicate buildFilters(Eia entity, CriteriaBuilder cb,
			Root<Eia> root, Join<Eia, Obu> obuJoin) {
		Predicate predicate = cb.conjunction();
		if (entity.getResponsibleRole() != null) {
			// logger.log(Level.INFO, "responsible role");
			final ParameterExpression<Role> p = cb.parameter(Role.class,
					"baseRole");
			predicate = cb.and(predicate,
					cb.equal(root.<Role> get("responsibleRole"), p));
		}
		if (entity.getCode() != null) {
			// logger.log(Level.INFO, "code");
			final ParameterExpression<String> p = cb.parameter(String.class,
					"code");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("code"), p));
		}
		if (entity.getEiaType() != null) {
			// logger.log(Level.INFO, "eiatype");
			final ParameterExpression<EiaType> p = cb.parameter(EiaType.class,
					"eiaType");
			predicate = cb.and(predicate,
					cb.equal(root.<EiaType> get("eiaType"), p));
		}
		if (entity.getObu() != null) {
			final Obu obu = entity.getObu();
			if (obu.getId() > 0) {
				// logger.log(Level.INFO, "obu");
				final ParameterExpression<Long> p = cb.parameter(Long.class,
						"obuId");
				final Path<Long> obuId = obuJoin.<Long> get("id");
				predicate = cb.and(predicate, cb.equal(obuId, p));
			}
			if (obu.getBpi() != null) {
				// logger.log(Level.INFO, "obu.bpi");
				final ParameterExpression<Bpi> p = cb.parameter(Bpi.class,
						"obuBpi");
				final Path<Bpi> obuBpi = obuJoin.<Bpi> get("bpi");
				predicate = cb.and(predicate, cb.equal(obuBpi, p));
			}
		}
		if (entity.getSerialNumber() != null) {
			// logger.log(Level.INFO, "serial");
			final ParameterExpression<String> p = cb.parameter(String.class,
					"serialNumber");
			predicate = cb.and(predicate,
					cb.equal(root.<String> get("serialNumber"), p));
		}
		if (entity.getState() != null) {
			// logger.log(Level.INFO, "state");
			final ParameterExpression<EiaStateEnum> p = cb.parameter(
					EiaStateEnum.class, "state");
			predicate = cb.and(predicate,
					cb.equal(root.<EiaStateEnum> get("state"), p));
		}
		if (entity.getActualCost() != null) {
			// logger.log(Level.INFO, "actualCost");
			final ParameterExpression<BigDecimal> p = cb.parameter(
					BigDecimal.class, "actualCost");
			predicate = cb.and(predicate,
					cb.equal(root.<BigDecimal> get("actualCost"), p));
		}
		if (entity.getWorkingArea() != null) {
			// logger.log(Level.INFO, "workingarea");
			final ParameterExpression<WorkingArea> p = cb.parameter(
					WorkingArea.class, "workingArea");
			predicate = cb.and(predicate,
					cb.equal(root.<WorkingArea> get("workingArea"), p));
		}
		if (entity.getFacility() != null) {
			// logger.log(Level.INFO, "facility");
			final ParameterExpression<Facility> p = cb.parameter(
					Facility.class, "facility");
			predicate = cb.and(predicate,
					cb.equal(root.<Facility> get("facility"), p));
		}
		return predicate;
	}

	@Override
	public List<Long> countByState() throws GHAEJBException {
		// TODO: EXCEPCIONES
		final List<Long> res = new ArrayList<Long>(EiaStateEnum.values().length);
		for (int i = 0; i < res.size(); ++i) {
			long next = 0L;
			try {
				next = em.createNamedQuery("Eia.countByState", Long.class)
						.setParameter("state", EiaStateEnum.values()[i])
						.getSingleResult();
			} catch (final Exception e) {
				next = 0;
			} finally {
				res.set(i, next);
			}
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id) throws GHAEJBException {
		try {
			final Eia entity = em.find(Eia.class, Id);
			em.remove(entity);
			return true;
		} catch (final Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete eia=" + Eia.class.getName()
							+ " with id=" + Long.toString(Id), e);
			throw super.generateGHAEJBException("eia-delete-fail", em);
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
			final CriteriaBuilder cb = em.getCriteriaBuilder();
			final CriteriaQuery<Eia> cQuery = cb.createQuery(Eia.class);
			final Root<Eia> root = cQuery.from(Eia.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("id")));

			Predicate criteria;
			if (entity.getObu() != null) {
				final Join<Eia, Obu> obuJoin = root.join("obu");
				criteria = buildFilters(entity, cb, root, obuJoin);
			} else
				criteria = buildFilters(entity, cb, root, null);

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
		} catch (final Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los Eia utilizando otro Eia", e);
			throw super.generateGHAEJBException("eia-findByEia-fail", em);
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
		} catch (final Exception e) {
			logger.log(Level.INFO, "Error: finding eia by eiatype", e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail", em);
		}
	}

	@Override
	public List<Eia> findDamagedAndInMaintenance(EiaType eiaType)
			throws GHAEJBException {
		try {
			final ArrayList<EiaStateEnum> stateList = new ArrayList<EiaStateEnum>();
			stateList.add(EiaStateEnum.DAMAGED);
			stateList.add(EiaStateEnum.MAINTENANCE);

			final String stringQuery = "SELECT e FROM Eia e WHERE e.eiaType = :eiaType AND e.state IN :eiaStates order by e.id";
			final List<Eia> resultList = em.createQuery(stringQuery, Eia.class)
					.setParameter("eiaType", eiaType)
					.setParameter("eiaStates", stateList).getResultList();

			return resultList;
		} catch (final Exception e) {
			final String stringException = "Error: finding eia by eiatype and state DAMAGED or MAINTENANCE";
			logger.log(Level.INFO, stringException, e);
			throw super.generateGHAEJBException("eia-findByEiaType-fail", em);
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
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: finding eia by id", e);
			throw super.generateGHAEJBException("eia-find-fail", em);
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
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eias", ex);
			throw super.generateGHAEJBException("eia-getAll-fail", em);
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
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all eias", ex);
			throw super.generateGHAEJBException("eia-getAll-fail", em);
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
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving eia ", e);
			throw super.generateGHAEJBException("eia-save-fail", em);
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
			final Eia res = em.merge(eia);
			return res;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eia ", e);
			throw super.generateGHAEJBException("eia-update-fail", em);
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Eia> findComponents(Eia entity, EiaType eiaType)
			throws GHAEJBException {
		final List<EiaTypeComponent> eiatypes = eiaTypeComponentService
				.findByParentEiaType(eiaType);
		final List<Eia> eias = find(entity);
		final List<Eia> result = new ArrayList<Eia>();
		for (final Eia eia : eias)
			for (final EiaTypeComponent eiaType2 : eiatypes)
				if (eiaType2.getEiaType().getCode()
						.equals(eia.getEiaType().getCode()))
					result.add(eia);
		return result;

	}
}
