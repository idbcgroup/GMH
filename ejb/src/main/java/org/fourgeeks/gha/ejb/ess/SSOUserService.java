/**
 * 
 */
package org.fourgeeks.gha.ejb.ess;

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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Citizen;

/**
 * @author emiliot
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
			Root<SSOUser> root, Join<SSOUser, Citizen> citiJoin) {
		Predicate predicate = cb.conjunction();
		if(ssoUser.getUserName() != null){
			ParameterExpression<String> p = cb.parameter(String.class, "userName");
			predicate = cb.and(predicate, cb.like(cb.lower(root.<String>get("userName")), p));
		}
		
		if(ssoUser.getBpu() != null){
			Bpu bpu = ssoUser.getBpu();
			//add the bpu filters here
			
			if(bpu.getCitizen() != null){
				Citizen citizen = bpu.getCitizen();
				
				if(citizen.getFirstName() != null){
					ParameterExpression<String> p = cb.parameter(String.class, "firstName");
					predicate = cb.and(predicate, cb.like(cb.lower(citiJoin.<String>get("firstName")), p));
				}
			}
		}
		
		return predicate;
	}
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			SSOUser entity = em.find(SSOUser.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete SSOUser", e);
			throw new EJBException("ERROR: unable to delete SSOUser "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#find(org.fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public List<SSOUser> find(SSOUser ssoUser) throws EJBException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<SSOUser> cQuery = cb.createQuery(SSOUser.class);
			Root<SSOUser> root = cQuery.from(SSOUser.class);
			Join<SSOUser,Citizen> citiJoin = root.join("bpu").join("citizen");
			
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("userName")));
			Predicate criteria = buildFilters(ssoUser, cb, root, citiJoin);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<SSOUser> q = em.createQuery(cQuery);

			if (ssoUser.getUserName() != null)
				q.setParameter("userName", "%"+ssoUser.getUserName()+"%");
			
			if(ssoUser.getBpu() != null){
				Bpu bpu = ssoUser.getBpu();
				//add bpu parameters here
				
				if(bpu.getCitizen() != null){
					Citizen citizen = bpu.getCitizen();
					
					if(citizen.getFirstName() != null){
						q.setParameter("firstName", "%"+citizen.getFirstName()+"%");
					}
				}
			}

			return q.getResultList();

		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"Error obteniendo los SSOUsers por SSOUser", e);
			throw new EJBException(
					"Error obteniendo los SSOUsers por SSOUser "
							+ e.getCause().getMessage());
		}
	}



	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#find(long)
	 */
	@Override
	public SSOUser find(long Id) throws EJBException {
		try {
			return em.find(SSOUser.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding SSOUser", e);
			throw new EJBException("ERROR: finding SSOUser "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#getAll()
	 */
	@Override
	public List<SSOUser> getAll() throws EJBException {
		try {
			return em.createNamedQuery("SSOUser.getAll", SSOUser.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all SSOUser", ex);
			throw new EJBException("Error obteniendo todas las SSOUser"
					+ ex.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#save(org.fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public SSOUser save(SSOUser ssoUser) throws EJBException {
		try {
			em.persist(ssoUser);
			em.flush();
			return em.find(SSOUser.class, ssoUser.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving SSOUser ", e);
			throw new EJBException("ERROR: saving SSOUser "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#update(org.fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public SSOUser update(SSOUser ssoUser) throws EJBException {
		try {
			SSOUser res = em.merge(ssoUser);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update SSOUser ", e);
			throw new EJBException("ERROR: no se puede actualizar el SSOUser "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#findByUsername(java.lang.String)
	 */
	@Override
	public SSOUser findByUsername(String userName) throws EJBException {
		try {
			return em.createNamedQuery("SSOUser.findByUserName", SSOUser.class)
					.setParameter("userName", userName).getSingleResult();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error finding SSOUser by username", ex);
			throw new EJBException("Error obteniendo el SSOUser por username"
					+ ex.getCause().getMessage());
		}
	}

}
