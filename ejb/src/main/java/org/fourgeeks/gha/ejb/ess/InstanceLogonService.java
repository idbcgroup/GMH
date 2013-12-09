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

import org.fourgeeks.gha.domain.ess.InstanceLogon;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless
public class InstanceLogonService extends GHAEJBExceptionImpl implements
		InstanceLogonServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(InstanceLogonService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			InstanceLogon entity = em.find(InstanceLogon.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete InstanceLogon", e);
			throw super.generateGHAEJBException("instanceLogon-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#find(org.fourgeeks
	 * .gha.domain.ess.InstanceLogon)
	 */
	@Override
	public List<InstanceLogon> find(InstanceLogon instanceLogon)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("InstanceLogon.findByInstanceLogon",
							InstanceLogon.class)
					.setParameter("instanceLogon", instanceLogon)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO,
					"Error: finding InstanceLogon by InstanceLogon", e);
			throw super.generateGHAEJBException(
					"instanceLogon-findByInstanceLogon-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#find(long)
	 */
	@Override
	public InstanceLogon find(long Id) throws GHAEJBException {
		try {
			return em.find(InstanceLogon.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding InstanceLogon", e);
			throw super.generateGHAEJBException("instanceLogon-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#getAll()
	 */
	@Override
	public List<InstanceLogon> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("InstanceLogon.getAll",
					InstanceLogon.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all InstanceLogon", ex);
			throw super.generateGHAEJBException("instanceLogon-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#save(org.fourgeeks
	 * .gha.domain.ess.InstanceLogon)
	 */
	@Override
	public InstanceLogon save(InstanceLogon instanceLogon)
			throws GHAEJBException {
		try {
			em.persist(instanceLogon);
			em.flush();
			return em.find(InstanceLogon.class, instanceLogon.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving InstanceLogon ", e);
			throw super.generateGHAEJBException("instanceLogon-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#update(org.fourgeeks
	 * .gha.domain.ess.InstanceLogon)
	 */
	@Override
	public InstanceLogon update(InstanceLogon instanceLogon)
			throws GHAEJBException {
		try {
			InstanceLogon res = em.merge(instanceLogon);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update InstanceLogon ", e);
			throw super.generateGHAEJBException("instanceLogon-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}
