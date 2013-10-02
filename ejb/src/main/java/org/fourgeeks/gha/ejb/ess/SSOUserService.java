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

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.exceptions.EJBException;

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

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#find(org.fourgeeks.gha
	 * .domain.ess.SSOUser)
	 */
	@Override
	public List<SSOUser> find(SSOUser ssoUser) throws EJBException {
		try {
			return em.createNamedQuery("SSOUser.findBySSOUser", SSOUser.class)
					.setParameter("ssoUser", ssoUser).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error finding SSOUser by ssoUser", ex);
			throw new EJBException("Error obteniendo el SSOUser por ssoUser"
					+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#save(org.fourgeeks.gha
	 * .domain.ess.SSOUser)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.SSOUserServiceRemote#update(org.fourgeeks.gha
	 * .domain.ess.SSOUser)
	 */
	@Override
	public SSOUser update(SSOUser ssoUser) throws EJBException {
		try {
			SSOUser res = em.merge(ssoUser);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update SSOUser ", e);
			throw new EJBException("ERROR: no se puede actualizar el SSOUser "
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
