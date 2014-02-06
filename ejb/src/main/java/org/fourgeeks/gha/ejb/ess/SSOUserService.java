/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.enu.DocumentTypeEnum;
import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.ejb.GHAEJBExceptionService;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class SSOUserService extends GHAEJBExceptionService implements
		SSOUserServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(SSOUserService.class
			.getName());

	/**
	 * @param ssoUser
	 * @param cb
	 * @param root
	 * @param citiJoin
	 * @return
	 */
	private static Predicate buildFilters(SSOUser ssoUser, CriteriaBuilder cb,
			Root<SSOUser> root) {
		Predicate predicate = cb.conjunction();
		if (ssoUser.getUserName() != null) {
			final ParameterExpression<String> p = cb.parameter(String.class,
					"userName");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("userName")), p));
		}

		if (ssoUser.getUserLogonStatus() != null) {
			final ParameterExpression<UserLogonStatusEnum> p = cb.parameter(
					UserLogonStatusEnum.class, "status");
			predicate = cb.and(predicate, cb.equal(
					root.<UserLogonStatusEnum> get("userLogonStatus"), p));
		}

		if (ssoUser.getBpu() != null) {
			final Bpu bpu = ssoUser.getBpu();
			final Join<SSOUser, Bpu> joinBpu = root.join("bpu");
			// add the bpu filters here

			if (bpu.getCitizen() != null) {
				final Citizen citizen = bpu.getCitizen();
				final Join<Bpu, Citizen> joinCitizen = joinBpu.join("citizen");

				if (citizen.getFirstName() != null) {
					final ParameterExpression<String> p = cb.parameter(
							String.class, "firstName");
					predicate = cb
							.and(predicate, cb.like(cb.lower(joinCitizen
									.<String> get("firstName")), p));
				}
				if (citizen.getSecondName() != null) {
					final ParameterExpression<String> p = cb.parameter(
							String.class, "secondName");
					predicate = cb.and(predicate,
							cb.like(cb.lower(joinCitizen
									.<String> get("secondName")), p));
				}
				if (citizen.getFirstLastName() != null) {
					final ParameterExpression<String> p = cb.parameter(
							String.class, "firstLastName");
					predicate = cb.and(predicate,
							cb.like(cb.lower(joinCitizen
									.<String> get("firstLastName")), p));
				}
				if (citizen.getSecondLastName() != null) {
					final ParameterExpression<String> p = cb.parameter(
							String.class, "secondLastName");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen
							.<String> get("secondLastName")), p));
				}
				if (citizen.getIdType() != null) {
					final ParameterExpression<DocumentTypeEnum> p = cb
							.parameter(DocumentTypeEnum.class, "idType");
					predicate = cb.and(predicate, cb.equal(
							joinCitizen.<DocumentTypeEnum> get("idType"), p));
				}

				if (citizen.getIdNumber() != null) {
					final ParameterExpression<String> p = cb.parameter(
							String.class, "idNumber");
					predicate = cb.and(predicate, cb.like(
							cb.lower(joinCitizen.<String> get("idNumber")), p));
				}
				if (citizen.getPrimaryEmail() != null) {
					final ParameterExpression<String> p = cb.parameter(
							String.class, "primaryEmail");
					predicate = cb.and(predicate, cb.like(
							cb.lower(joinCitizen.<String> get("primaryEmail")),
							p));
				}
				if (citizen.getAlternativeEmail() != null) {
					final ParameterExpression<String> p = cb.parameter(
							String.class, "alternativeEmail");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen
							.<String> get("alternativeEmail")), p));
				}
				if (citizen.getGender() != null) {
					final ParameterExpression<GenderTypeEnum> p = cb.parameter(
							GenderTypeEnum.class, "gender");
					predicate = cb.and(predicate, cb.equal(
							joinCitizen.<GenderTypeEnum> get("gender"), p));
				}
			}
		}
		return predicate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			final SSOUser entity = em.find(SSOUser.class, Id);
			em.remove(entity);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete SSOUser", e);
			throw super.generateGHAEJBException("ssoUser-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#find(org.fourgeeks.gha
	 * .domain.ess.SSOUser)
	 */
	@Override
	public List<SSOUser> find(SSOUser ssoUser) throws GHAEJBException {
		try {
			final CriteriaBuilder cb = em.getCriteriaBuilder();
			final CriteriaQuery<SSOUser> cQuery = cb.createQuery(SSOUser.class);
			final Root<SSOUser> root = cQuery.from(SSOUser.class);

			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("userName")));
			final Predicate criteria = buildFilters(ssoUser, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			final TypedQuery<SSOUser> q = em.createQuery(cQuery);

			if (ssoUser.getUserName() != null)
				q.setParameter("userName", "%" + ssoUser.getUserName() + "%");
			if (ssoUser.getUserLogonStatus() != null)
				q.setParameter("status", ssoUser.getUserLogonStatus());

			if (ssoUser.getBpu() != null) {
				final Bpu bpu = ssoUser.getBpu();
				// add bpu parameters here

				// q.setParameter("bpi", em.find(Bpi.class, 1L));

				if (bpu.getCitizen() != null) {
					final Citizen citizen = bpu.getCitizen();

					if (citizen.getFirstName() != null)
						q.setParameter("firstName", "%"
								+ citizen.getFirstName().toLowerCase() + "%");
					if (citizen.getSecondName() != null)
						q.setParameter("secondName", "%"
								+ citizen.getSecondName().toLowerCase() + "%");
					if (citizen.getFirstLastName() != null)
						q.setParameter("firstLastName", "%"
								+ citizen.getFirstLastName().toLowerCase()
								+ "%");
					if (citizen.getSecondLastName() != null)
						q.setParameter("secondLastName", "%"
								+ citizen.getSecondLastName().toLowerCase()
								+ "%");
					if (citizen.getIdType() != null)
						q.setParameter("idType", citizen.getIdType());
					if (citizen.getIdNumber() != null)
						q.setParameter("idNumber", "%"
								+ citizen.getIdNumber().toLowerCase() + "%");
					if (citizen.getPrimaryEmail() != null)
						q.setParameter("primaryEmail", "%"
								+ citizen.getPrimaryEmail().toLowerCase() + "%");
					if (citizen.getAlternativeEmail() != null)
						q.setParameter("alternativeEmail", "%"
								+ citizen.getAlternativeEmail().toLowerCase()
								+ "%");
					if (citizen.getGender() != null)
						q.setParameter("gender", citizen.getGender());
				}
			}

			return q.getResultList();

		} catch (final Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los SSOUsers por SSOUser", e);
			throw super.generateGHAEJBException("ssoUser-findBySsoUser-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#find(long)
	 */
	@Override
	public SSOUser find(long Id) throws GHAEJBException {
		try {
			return em.find(SSOUser.class, Id);
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: finding SSOUser", e);
			throw super.generateGHAEJBException("ssoUser-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#getAll()
	 */
	@Override
	public List<SSOUser> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("SSOUser.getAll", SSOUser.class)
					.getResultList();
		} catch (final Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all SSOUser", ex);
			throw super.generateGHAEJBException("ssoUser-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#save(org.fourgeeks.gha
	 * .domain.ess.SSOUser)
	 */
	@Override
	public SSOUser save(SSOUser ssoUser) throws GHAEJBException {
		try {
			if (ssoUser.getBpu() != null && ssoUser.getBpu().getId() <= 0) {
				final Bpu bpu = ssoUser.getBpu();
				if (bpu.getCitizen() != null && bpu.getCitizen().getId() <= 0) {
					final Citizen citizen = bpu.getCitizen();
					if (citizen.getLegalEntity() != null
							&& citizen.getLegalEntity().getId() <= 0) {
						em.persist(citizen.getLegalEntity());
					}
					em.persist(citizen);
				}
				em.persist(bpu);
			}

			em.persist(ssoUser);
			em.flush();
			return em.find(SSOUser.class, ssoUser.getId());
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: saving SSOUser ", e);
			throw super.generateGHAEJBException("ssoUser-save-fail", em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#update(org.fourgeeks.gha
	 * .domain.ess.SSOUser)
	 */
	@Override
	public SSOUser update(SSOUser ssoUser) throws GHAEJBException {
		try {
			final SSOUser res = em.merge(ssoUser);
			em.flush();
			return res;
		} catch (final Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update SSOUser ", e);
			throw super.generateGHAEJBException("ssoUser-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#findByUsername(java.lang
	 * .String)
	 */
	@Override
	public SSOUser findByUsername(String userName) throws GHAEJBException {
		try {
			final SSOUser user = em
					.createNamedQuery("SSOUser.findByUserName", SSOUser.class)
					.setParameter("userName", userName).getSingleResult();

			if (user.getUserLogonStatus() == UserLogonStatusEnum.BLOCKED)
				throw super.generateGHAEJBException("LOGIN004", em);

			return user;
		} catch (final NoResultException ex) {
			logger.info("username: " + userName + " not found. Error:"
					+ ex.getMessage());
			throw super.generateGHAEJBException("LOGIN005", em);
		} catch (final GHAEJBException ex) {
			logger.info("username: " + userName + "blocked" + ex.getMessage());
			throw super.generateGHAEJBException("LOGIN004", em);
		} catch (final Exception ex) {
			logger.info("Error finding SSOUser by username. Error: "
					+ ex.getMessage());
			throw super.generateGHAEJBException("ssoUser-findByUsername-fail",
					em);
		}
	}
}
