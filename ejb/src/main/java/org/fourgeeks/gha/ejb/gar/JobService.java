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

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.gar.JobCategory;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.ServiceResource;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author alacret, vivi.torresg
 * 
 */
@Stateless
public class JobService extends GHAEJBExceptionService implements
		JobServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger.getLogger(JobService.class
			.getName());

	private static Predicate buildFilters(Job entity, CriteriaBuilder cb,
			Root<Job> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getJobCategory() != null) {
			ParameterExpression<JobCategory> p = cb.parameter(
					JobCategory.class, "jobCategory");
			criteria = cb.and(criteria,
					cb.equal(root.<JobCategory> get("jobCategory"), p));
		}

		if (entity.getObu() != null) {
			ParameterExpression<Obu> p = cb.parameter(Obu.class, "obu");
			criteria = cb.and(criteria, cb.equal(root.<Obu> get("obu"), p));
		}

		if (entity.getRole() != null) {
			ParameterExpression<Role> p = cb.parameter(Role.class, "role");
			criteria = cb.and(criteria, cb.equal(root.<Role> get("role"), p));
		}

		if (entity.getServiceResource() != null) {
			ParameterExpression<ServiceResource> p = cb.parameter(
					ServiceResource.class, "serv");
			criteria = cb.and(criteria,
					cb.equal(root.<ServiceResource> get("serv"), p));
		}

		return criteria;
	}

	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			Job entity = em.find(Job.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Job", e);
			throw super.generateGHAEJBException("obu-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public Job find(long Id) throws GHAEJBException {
		try {
			return em.find(Job.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Job", e);
			throw super.generateGHAEJBException("job-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<Job> find(Job job) throws GHAEJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Job> cQuery = cb.createQuery(Job.class);
			Root<Job> root = cQuery.from(Job.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.get("id")));

			Predicate criteria = buildFilters(job, cb, root);
			cQuery.where(criteria);

			TypedQuery<Job> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (job.getJobCategory() != null) {
					q.setParameter("jobCategory", job.getJobCategory());
				}
				if (job.getObu() != null) {
					q.setParameter("obu", job.getObu());
				}
				if (job.getRole() != null) {
					q.setParameter("role", job.getRole());
				}
				if (job.getServiceResource() != null) {
					q.setParameter("serv", job.getServiceResource());
				}
			}

			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Job by Job", e);
			throw super.generateGHAEJBException("job-findByJob-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public List<Job> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Job.getAll", Job.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Job", ex);
			throw super.generateGHAEJBException("job-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	@Override
	public Job save(Job entity) throws GHAEJBException {
		try {
			em.persist(entity);
			em.flush();
			return em.find(Job.class, entity.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Job ", e);
			throw super.generateGHAEJBException("job-save-fail",
					RuntimeParameters.getLang(), em);
		}

	}

	@Override
	public Job update(Job entity) throws GHAEJBException {
		try {
			Job res = em.merge(entity);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Job ", e);
			throw super.generateGHAEJBException("job-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}