package org.fourgeeks.gha.ejb.ess;

import java.sql.Timestamp;
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

import org.fourgeeks.gha.domain.enu.ServiceOderState;
import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;

/**
 * Session Bean implementation class MaintenanceServiceOrder
 */
@Stateless
public class MaintenanceServiceOrderService extends GHAEJBExceptionService
		implements MaintenanceServiceOrderLocal {

	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(MaintenanceServiceOrderService.class.getName());

	private static Predicate buildFilters(MaintenanceServiceOrder entity,
			CriteriaBuilder cb, Root<MaintenanceServiceOrder> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getMaintenance() != null) {
			ParameterExpression<EiaMaintenance> p = cb.parameter(
					EiaMaintenance.class, "maintenance");
			criteria = cb.and(criteria,
					cb.equal(root.<EiaMaintenance> get("maintenance"), p));
		}
		if (entity.getMaintenanceProvider() != null) {
			ParameterExpression<Bsp> p = cb.parameter(Bsp.class, "bsp");
			criteria = cb.and(criteria, cb.equal(root.<Bsp> get("bsp"), p));
		}
		if (entity.getOpeningTimestamp() != null) {
			ParameterExpression<Timestamp> p = cb.parameter(Timestamp.class,
					"openingTimesptamp");
			criteria = cb.and(criteria,
					cb.equal(root.<Timestamp> get("openingTimesptamp"), p));
		}
		if (entity.getServiceOrderNumber() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"serviceOrderNumber");
			criteria = cb.and(criteria,
					cb.equal(root.<String> get("serviceOrderNumber"), p));
		}
		if (entity.getState() != null) {
			ParameterExpression<ServiceOderState> p = cb.parameter(
					ServiceOderState.class, "state");
			criteria = cb.and(criteria,
					cb.equal(root.<ServiceOderState> get("state"), p));
		}

		return criteria;
	}

	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			MaintenanceServiceOrder entity = em.find(
					MaintenanceServiceOrder.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to delete MaintenanceServiceOrder", e);
			throw super.generateGHAEJBException(
					"maintenanceServiceOrder-delete-fail", em);
		}
	}

	@Override
	public MaintenanceServiceOrder find(long Id) throws GHAEJBException {
		try {
			return em.find(MaintenanceServiceOrder.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Bsp", e);
			throw super.generateGHAEJBException("bsp-find-fail", em);
		}
	}

	@Override
	public List<MaintenanceServiceOrder> find(MaintenanceServiceOrder entity)
			throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<MaintenanceServiceOrder> cQuery = cb
					.createQuery(MaintenanceServiceOrder.class);
			Root<MaintenanceServiceOrder> root = cQuery
					.from(MaintenanceServiceOrder.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("id")));

			Predicate criteria = buildFilters(entity, cb, root);
			cQuery.where(criteria);

			TypedQuery<MaintenanceServiceOrder> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (entity.getMaintenance() != null) {
					q.setParameter("maintenance", entity.getMaintenance());
				}
				if (entity.getMaintenanceProvider() != null) {
					q.setParameter("bsp", entity.getMaintenanceProvider());
				}
				if (entity.getOpeningTimestamp() != null) {
					q.setParameter("openingTimesptamp",
							entity.getOpeningTimestamp());
				}
				if (entity.getServiceOrderNumber() != null) {
					q.setParameter("serviceOrderNumber",
							entity.getServiceOrderNumber());
				}
				if (entity.getState() != null) {
					q.setParameter("state", entity.getState());
				}
			}

			return q.getResultList();

		} catch (Exception e) {
			logger.log(
					Level.INFO,
					"Error: finding MaintenanceServiceOrder by MaintenanceServiceOrder",
					e);
			throw super
					.generateGHAEJBException(
							"maintenanceServiceOrder-findByMaintenanceServiceOrder-fail",
							em);
		}
	}

	@Override
	public List<MaintenanceServiceOrder> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("MaintenanceServiceOrder.getAll",
					MaintenanceServiceOrder.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error retrieving all MaintenanceServiceOrder", ex);
			throw super.generateGHAEJBException(
					"maintenanceServiceOrder-getAll-fail", em);
		}
	}

	@Override
	public MaintenanceServiceOrder save(MaintenanceServiceOrder entity)
			throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(MaintenanceServiceOrder.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving MaintenanceServiceOrder ", e);
			throw super.generateGHAEJBException(
					"maintenanceServiceOrder-save-fail", em);
		}

	}

	@Override
	public MaintenanceServiceOrder update(MaintenanceServiceOrder entity)
			throws GHAEJBException {
		try {
			MaintenanceServiceOrder res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update MaintenanceServiceOrder ", e);
			throw super.generateGHAEJBException(
					"maintenanceServiceOrder-update-fail", em);
		}
	}

}
