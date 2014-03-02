/**
 * 
 */
package org.fourgeeks.gha.ejb.mix;

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

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class CitizenService extends GHAEJBExceptionService implements
		CitizenServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(CitizenService.class
			.getName());

	private static Predicate buildFilters(Citizen entity, CriteriaBuilder cb,
			Root<Citizen> root) {
		Predicate criteria = cb.conjunction();

		if (entity.getFirstName() != null) {
			final ParameterExpression<String> p = cb.parameter(String.class,
					"firstName");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("firstName")), p));
		}

		if (entity.getSecondName() != null) {
			final ParameterExpression<String> p = cb.parameter(String.class,
					"secondName");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("secondName")), p));
		}

		if (entity.getFirstLastName() != null) {
			final ParameterExpression<String> p = cb.parameter(String.class,
					"firstLastName");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("firstLastName")), p));
		}

		if (entity.getSecondLastName() != null) {
			final ParameterExpression<String> p = cb.parameter(String.class,
					"secondLastName");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("secondLastName")), p));
		}

		if (entity.getPrimaryEmail() != null) {
			final ParameterExpression<String> p = cb.parameter(String.class,
					"primaryEmail");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("primaryEmail")), p));
		}

		if (entity.getIdType() != null) {
			final ParameterExpression<DocumentTypeEnum> p = cb.parameter(
					DocumentTypeEnum.class, "idType");
			criteria = cb.and(criteria,
					cb.equal(root.<DocumentTypeEnum> get("idType"), p));
		}

		if (entity.getIdNumber() != null) {
			final ParameterExpression<String> p = cb.parameter(String.class,
					"idNumber");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("idNumber")), p));
		}

		if (entity.getGender() != null) {
			final ParameterExpression<GenderTypeEnum> p = cb.parameter(
					GenderTypeEnum.class, "gender");
			criteria = cb.and(criteria,
					cb.equal(root.<GenderTypeEnum> get("gender"), p));
		}

		if (entity.getNationality() != null) {
			final ParameterExpression<String> p = cb.parameter(String.class,
					"nationality");
			criteria = cb.and(criteria,
					cb.like(cb.lower(root.<String> get("nationality")), p));
		}

		if (entity.getLegalEntity() != null) {
			final ParameterExpression<GenderTypeEnum> p = cb.parameter(
					GenderTypeEnum.class, "legalEntity");
			criteria = cb.and(criteria,
					cb.equal(root.<GenderTypeEnum> get("legalEntity"), p));
		}

		return criteria;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#delete(long)
	 */
	@Override
	public void delete(List<Citizen> citizens) throws GHAEJBException {
		try {
			for (final Citizen citizen : citizens) {
				final Citizen entity = em.find(Citizen.class, citizen.getId());
				em.remove(entity);
			}

		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Citizen", e);
			throw super.generateGHAEJBException("citizen-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#find(org.fourgeeks.gha
	 * .domain.mix.Citizen)
	 */
	@Override
	public List<Citizen> find(Citizen citizen) throws GHAEJBException {
		try {
			final CriteriaBuilder cb = em.getCriteriaBuilder();
			final CriteriaQuery<Citizen> cQuery = cb.createQuery(Citizen.class);
			final Root<Citizen> root = cQuery.from(Citizen.class);
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("firstLastName")));

			final Predicate criteria = buildFilters(citizen, cb, root);
			TypedQuery<Citizen> q;

			if (criteria.getExpressions().size() <= 0) {
				q = em.createQuery(cQuery);
			} else {
				cQuery.where(criteria);
				q = em.createQuery(cQuery);

				if (citizen.getFirstName() != null) {
					q.setParameter("firstName", "%"
							+ citizen.getFirstName().toLowerCase() + "%");
				}

				if (citizen.getSecondName() != null) {
					q.setParameter("secondName", "%"
							+ citizen.getSecondName().toLowerCase() + "%");
				}

				if (citizen.getFirstLastName() != null) {
					q.setParameter("firstLastName", "%"
							+ citizen.getFirstLastName().toLowerCase() + "%");
				}

				if (citizen.getSecondLastName() != null) {
					q.setParameter("secondLastName", "%"
							+ citizen.getSecondLastName().toLowerCase() + "%");
				}

				if (citizen.getPrimaryEmail() != null) {
					q.setParameter("primaryEmail", "%"
							+ citizen.getPrimaryEmail().toLowerCase() + "%");
				}

				if (citizen.getIdType() != null) {
					q.setParameter("idType", citizen.getIdType());
				}

				if (citizen.getIdNumber() != null) {
					q.setParameter("idNumber", "%"
							+ citizen.getIdNumber().toLowerCase() + "%");
				}

				if (citizen.getGender() != null) {
					q.setParameter("gender", citizen.getGender());
				}

				if (citizen.getNationality() != null) {
					q.setParameter("nacionality", "%"
							+ citizen.getNationality().toLowerCase() + "%");
				}

				if (citizen.getLegalEntity() != null) {
					q.setParameter("legalEntity", citizen.getLegalEntity());
				}
			}
			return q.getResultList();
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error finding Citizen by citizen", ex);
			throw super.generateGHAEJBException("citizen-findByCitizen-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#find(long)
	 */
	@Override
	public Citizen find(long Id) throws GHAEJBException {
		try {
			return em.find(Citizen.class, Id);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: finding Citizen", e);
			throw super.generateGHAEJBException("citizen-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#getAll()
	 */
	@Override
	public List<Citizen> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("Citizen.getAll", Citizen.class)
					.getResultList();
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all Citizen", ex);
			throw super.generateGHAEJBException("citizen-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#save(org.fourgeeks.gha
	 * .domain.mix.Citizen)
	 */
	@Override
	public Citizen save(Citizen citizen) throws GHAEJBException {
		try {
			em.persist(citizen.getLegalEntity());
			em.persist(citizen);
			em.flush();
			return em.find(Citizen.class, citizen.getId());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving Citizen ", e);
			throw super.generateGHAEJBException("citizen-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.mix.CitizenServiceRemote#update(org.fourgeeks.gha
	 * .domain.mix.Citizen)
	 */
	@Override
	public Citizen update(Citizen citizen) throws GHAEJBException {
		try {
			final Citizen res = em.merge(citizen);
			em.flush();
			return res;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Citizen ", e);
			throw super.generateGHAEJBException("citizen-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}