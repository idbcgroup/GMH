/**
 * 
 */
package org.fourgeeks.gha.ejb.gar;

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
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret, vivi.torresg
 * 
 */
@Stateless
public class ObuService extends GHAEJBExceptionService implements
		ObuServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(ObuService.class
			.getName());

	private static Predicate buildFilters(Obu entity, CriteriaBuilder cb,
			Root<Obu> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getBpi() != null) {
			ParameterExpression<Bpi> p = cb.parameter(Bpi.class, "bpi");
			criteria = cb.and(criteria, cb.equal(root.<Bpi> get("bpi"), p));
		}

		if (entity.getCode() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "code");
			criteria = cb.and(criteria, cb.equal(root.<String> get("code"), p));
		}

		if (entity.getName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("name")), p));
		}

		return criteria;
	}

	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Obu entity = em.find(Obu.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Obu", e);
			throw super.generateGHAEJBException("obu-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public Obu find(long Id) throws GHAEJBException {
		try {
			return em.find(Obu.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Obu", e);
			throw super.generateGHAEJBException("obu-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<Obu> find(Obu obu) throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Obu> cQuery = cb.createQuery(Obu.class);
			Root<Obu> root = cQuery.from(Obu.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("id")));

			Predicate criteria = buildFilters(obu, cb, root);
			cQuery.where(criteria);

			TypedQuery<Obu> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (obu.getBpi() != null) {
					q.setParameter("bpi", obu.getBpi());
				}
				if (obu.getCode() != null) {
					q.setParameter("code", obu.getCode());
				}
				if (obu.getName() != null) {
					q.setParameter("name", "%" + obu.getName().toLowerCase()
							+ "%");
				}
			}

			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Obu by Obu", e);
			throw super.generateGHAEJBException("obu-findByObu-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<Obu> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Obu.getAll", Obu.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Obu", ex);
			throw super.generateGHAEJBException("obu-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public Obu save(Obu entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(Obu.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Obu ", e);
			throw super.generateGHAEJBException("obu-save-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	@Override
	public Obu update(Obu entity) throws GHAEJBException {
		try {
			Obu res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Obu ", e);
			throw super.generateGHAEJBException("obu-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}