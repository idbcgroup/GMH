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

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
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
			Root<SSOUser> root) {
		Predicate predicate = cb.conjunction();
		if(ssoUser.getUserName() != null){
			ParameterExpression<String> p = cb.parameter(String.class, "userName");
			predicate = cb.and(predicate, cb.like(cb.lower(root.<String>get("userName")), p));
		}
		
		if(ssoUser.getBpu() != null){
			Bpu bpu = ssoUser.getBpu();
			Join<SSOUser, Bpu> joinBpu = root.join("bpu");
			//add the bpu filters here
			
			if(bpu.getCitizen() != null){
				Citizen citizen = bpu.getCitizen();
				Join<Bpu, Citizen> joinCitizen = joinBpu.join("citizen");
				
				if(citizen.getFirstName() != null){
					ParameterExpression<String> p = cb.parameter(String.class, "firstName");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen.<String>get("firstName")), p));
				}
				if(citizen.getSecondName() != null){
					ParameterExpression<String> p = cb.parameter(String.class, "secondName");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen.<String>get("secondName")), p));
				}
				if(citizen.getFirstLastName() != null){
					ParameterExpression<String> p = cb.parameter(String.class, "firstLastName");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen.<String>get("firstLastName")), p));
				}
				if(citizen.getSecondLastName() != null){
					ParameterExpression<String> p = cb.parameter(String.class, "secondLastName");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen.<String>get("secondLastName")), p));
				}
				if(citizen.getIdNumber() != null){
					ParameterExpression<String> p = cb.parameter(String.class, "idNumber");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen.<String>get("idNumber")), p));
				}
				if(citizen.getPrimaryEmail() != null){
					ParameterExpression<String> p = cb.parameter(String.class, "primaryEmail");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen.<String>get("primaryEmail")), p));
				}
				if(citizen.getAlternativeEmail() != null){
					ParameterExpression<String> p = cb.parameter(String.class, "alternativeEmail");
					predicate = cb.and(predicate, cb.like(cb.lower(joinCitizen.<String>get("alternativeEmail")), p));
				}
				if(citizen.getGender() != null){
					ParameterExpression<GenderTypeEnum> p = cb.parameter(GenderTypeEnum.class, "gender");
					predicate = cb.and(predicate, cb.equal(joinCitizen.<GenderTypeEnum> get("gender"), p));
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
			
			cQuery.select(root);
			cQuery.orderBy(cb.asc(root.<String> get("userName")));
			Predicate criteria = buildFilters(ssoUser, cb, root);

			if (criteria.getExpressions().size() == 0)
				return getAll();

			cQuery.where(criteria);
			TypedQuery<SSOUser> q = em.createQuery(cQuery);

			if (ssoUser.getUserName() != null)
				q.setParameter("userName", "%"+ssoUser.getUserName()+"%");
			
			if(ssoUser.getBpu() != null){
				Bpu bpu = ssoUser.getBpu();
				//add bpu parameters here
				
//				q.setParameter("bpi", em.find(Bpi.class, 1L));
				
				if(bpu.getCitizen() != null){
					Citizen citizen = bpu.getCitizen();
					
					if(citizen.getFirstName() != null)
						q.setParameter("firstName", "%" + citizen.getFirstName().toLowerCase() + "%");
					if(citizen.getSecondName() != null)
						q.setParameter("secondName", "%" + citizen.getSecondName().toLowerCase() + "%");
					if(citizen.getFirstLastName() != null)
						q.setParameter("firstLastName", "%" + citizen.getFirstLastName().toLowerCase() + "%");
					if(citizen.getSecondLastName() != null)
						q.setParameter("secondLastName", "%" + citizen.getSecondLastName().toLowerCase() + "%");
					if(citizen.getIdNumber() != null)
						q.setParameter("idNumber", "%" + citizen.getIdNumber().toLowerCase() + "%");
					if(citizen.getPrimaryEmail() != null)
						q.setParameter("primaryEmail", "%" + citizen.getPrimaryEmail().toLowerCase() + "%");
					if(citizen.getAlternativeEmail() != null)
						q.setParameter("alternativeEmail", "%" + citizen.getAlternativeEmail().toLowerCase() + "%");
					if(citizen.getGender() != null)
						q.setParameter("gender", citizen.getGender());
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
