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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.domain.ess.BpuFunction;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.domain.msg.GHAMessageId;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "ess.SSOUserService")
public class SSOUserService implements SSOUserServiceRemote {
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
	private Predicate buildFilters(SSOUser ssoUser, CriteriaBuilder cb,
			Root<SSOUser> root) {
		Predicate predicate = cb.conjunction();
		if (ssoUser.getUserName() != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"userName");
			predicate = cb.and(predicate,
					cb.like(cb.lower(root.<String> get("userName")), p));
		}

		if (ssoUser.getBpu() != null) {
			Bpu bpu = ssoUser.getBpu();
			Join<SSOUser, Bpu> joinBpu = root.join("bpu");
			// add the bpu filters here

			if (bpu.getCitizen() != null) {
				Citizen citizen = bpu.getCitizen();
				Join<Bpu, Citizen> joinCitizen = joinBpu.join("citizen");

				if (citizen.getFirstName() != null) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"firstName");
					predicate = cb
							.and(predicate, cb.like(cb.lower(joinCitizen
									.<String> get("firstName")), p));
				}
				if (citizen.getSecondName() != null) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"secondName");
					predicate = cb.and(predicate,
							cb.like(cb.lower(joinCitizen
									.<String> get("secondName")), p));
				}
				if (citizen.getFirstLastName() != null) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"firstLastName");
					predicate = cb.and(predicate,
							cb.like(cb.lower(joinCitizen
									.<String> get("firstLastName")), p));
				}
				if (citizen.getSecondLastName() != null) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"secondLastName");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen
							.<String> get("secondLastName")), p));
				}
				if (citizen.getIdNumber() != null) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"idNumber");
					predicate = cb.and(predicate, cb.like(
							cb.lower(joinCitizen.<String> get("idNumber")), p));
				}
				if (citizen.getPrimaryEmail() != null) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"primaryEmail");
					predicate = cb.and(predicate, cb.like(
							cb.lower(joinCitizen.<String> get("primaryEmail")),
							p));
				}
				if (citizen.getAlternativeEmail() != null) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"alternativeEmail");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen
							.<String> get("alternativeEmail")), p));
				}
				if (citizen.getGender() != null) {
					ParameterExpression<GenderTypeEnum> p = cb.parameter(
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
			SSOUser entity = em.find(SSOUser.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete SSOUser", e);
			throw new GHAEJBException("ERROR: unable to delete SSOUser "
					+ e.getCause().getMessage());
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
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SSOUser> cQuery = cb.createQuery(SSOUser.class);
			Root<SSOUser> root = cQuery.from(SSOUser.class);

			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("userName")));
			Predicate criteria = buildFilters(ssoUser, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<SSOUser> q = em.createQuery(cQuery);

			if (ssoUser.getUserName() != null)
				q.setParameter("userName", "%" + ssoUser.getUserName() + "%");

			if (ssoUser.getBpu() != null) {
				Bpu bpu = ssoUser.getBpu();
				// add bpu parameters here

				// q.setParameter("bpi", em.find(Bpi.class, 1L));

				if (bpu.getCitizen() != null) {
					Citizen citizen = bpu.getCitizen();

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

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los SSOUsers por SSOUser", e);
			throw new GHAEJBException(
					"Error obteniendo los SSOUsers por SSOUser "
							+ e.getCause().getMessage());
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
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding SSOUser", e);
			throw new GHAEJBException("ERROR: finding SSOUser "
					+ e.getCause().getMessage());
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
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all SSOUser", ex);
			throw new GHAEJBException("Error obteniendo todas las SSOUser"
					+ ex.getCause().getMessage());
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
				Bpu bpu = ssoUser.getBpu();
				if (bpu.getCitizen() != null && bpu.getCitizen().getId() <= 0) {
					Citizen citizen = bpu.getCitizen();
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
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving SSOUser ", e);
			throw new GHAEJBException("ERROR: saving SSOUser "
					+ e.getCause().getMessage());
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
			SSOUser res = em.merge(ssoUser);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update SSOUser ", e);
			throw new GHAEJBException(
					"ERROR: no se puede actualizar el SSOUser "
							+ e.getCause().getMessage());
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
			SSOUser user = em
					.createNamedQuery("SSOUser.findByUserName", SSOUser.class)
					.setParameter("userName", userName).getSingleResult();

			if (user.getUserLogonStatus() == UserLogonStatusEnum.BLOCKED)
				throw new GHAEJBException(new GHAMessage("LOGIN004"));

			return user;
		} catch (NoResultException ex) {
			logger.info("username: " + userName + " not found. Error:"
					+ ex.getMessage());
			GHAEJBException exception = new GHAEJBException();

			try {
				exception.setGhaMessage(em.find(
						GHAMessage.class,
						new GHAMessageId("LOGIN005", RuntimeParameters
								.getLang())));
			} catch (Exception e1) {
				exception.setGhaMessage(new GHAMessage(RuntimeParameters
						.getLang(), "generic-error-msg",
						"Error de sistema, intente más tarde."));
			}

			throw exception;
		} catch (GHAEJBException ex) {
			logger.info("username: " + userName + "blocked" + ex.getMessage());
			GHAEJBException exception = new GHAEJBException();

			try {
				exception.setGhaMessage(em.find(
						GHAMessage.class,
						new GHAMessageId("LOGIN004", RuntimeParameters
								.getLang())));
			} catch (Exception e1) {
				exception.setGhaMessage(new GHAMessage(RuntimeParameters
						.getLang(), "generic-error-msg",
						"Error de sistema, intente más tarde."));
			}
			throw exception;

		} catch (Exception ex) {
			logger.info("Error finding SSOUser by username. Error: "
					+ ex.getMessage());
			throw new GHAEJBException(new GHAMessage(
					RuntimeParameters.getLang(), "generic-error-msg",
					"Error de sistema, intente más tarde."));
		}
	}

	@Override
	public BpuFunction save(BpuFunction bpuFunction) throws GHAEJBException {
		try {
			em.persist(bpuFunction);
			em.flush();
			return em.find(BpuFunction.class, bpuFunction.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving BpuFunction ", e);
			throw new GHAEJBException("ERROR: saving BpuFunction "
					+ e.getCause().getMessage());
		}
	}

	@Override
	public void delete(BpuFunction bpuFunction) throws GHAEJBException {
		try {
			Query query = em.createNamedQuery("BpuFunction.delete")
					.setParameter("bpu", bpuFunction.getBpu())
					.setParameter("function", bpuFunction.getFunction());
			query.executeUpdate();
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: delete BpuFunction ", e);
			throw new GHAEJBException("ERROR: delete BpuFunction "
					+ e.getCause().getMessage());
		}
	}
}
