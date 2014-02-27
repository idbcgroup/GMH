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
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * @author alacret, vivi.torresg, naramirez
 * 
 */
@Stateless
public class BspService extends GHAEJBExceptionService implements
		BspServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(BspService.class
			.getName());

	private static Predicate buildFilters(Bsp entity, CriteriaBuilder cb,
			Root<Bsp> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getObu() != null) {
			ParameterExpression<Obu> p = cb.parameter(Obu.class, "obu");
			criteria = cb.and(criteria, cb.equal(root.<Obu> get("obu"), p));
		}

		return criteria;
	}

	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Bsp entity = em.find(Bsp.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Bsp", e);
			throw super.generateGHAEJBException("bsp-delete-fail", em);
		}
	}

	@Override
	public Bsp find(long Id) throws GHAEJBException {
		try {
			return em.find(Bsp.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Bsp", e);
			throw super.generateGHAEJBException("bsp-find-fail", em);
		}
	}

	@Override
	public List<Bsp> find(Bsp bsp) throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Bsp> cQuery = cb.createQuery(Bsp.class);
			Root<Bsp> root = cQuery.from(Bsp.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("id")));

			Predicate criteria = buildFilters(bsp, cb, root);
			cQuery.where(criteria);

			TypedQuery<Bsp> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (bsp.getObu() != null) {
					q.setParameter("obu", bsp.getObu());
				}
			}

			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Bsp by Bsp", e);
			throw super.generateGHAEJBException("bsp-findByBsp-fail", em);
		}
	}

	@Override
	public List<Bsp> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Bsp.getAll", Bsp.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Bsp", ex);
			throw super.generateGHAEJBException("bsp-getAll-fail", em);
		}
	}

	@Override
	public Bsp save(Bsp entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(Bsp.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Bsp ", e);
			throw super.generateGHAEJBException("bsp-save-fail", em);
		}

	}

	@Override
	public Bsp update(Bsp entity) throws GHAEJBException {
		try {
			Bsp res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Bsp ", e);
			throw super.generateGHAEJBException("bsp-update-fail", em);
		}
	}
}