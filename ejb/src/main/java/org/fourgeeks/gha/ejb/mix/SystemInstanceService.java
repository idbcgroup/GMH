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

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.mix.SystemInstance;
import org.fourgeeks.gha.ejb.GHAEJBExceptionImpl;
import org.fourgeeks.gha.ejb.RuntimeParameters;

/**
 * @author emiliot, vivi.torresg
 * 
 */
@Stateless(name = "mix.SystemInstanceService")
public class SystemInstanceService extends GHAEJBExceptionImpl implements
		SystemInstanceServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(SystemInstanceService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#delete(long)
	 */
	@Override
	public void delete(long Id) throws GHAEJBException {
		try {
			SystemInstance entity = em.find(SystemInstance.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete SystemInstance", e);
			throw super.generateGHAEJBException("systemInstance-delete-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#find(org.fourgeeks
	 * .gha.domain.mix.SystemInstance)
	 */
	@Override
	public List<SystemInstance> find(SystemInstance systemInstance)
			throws GHAEJBException {
		try {
			return em
					.createNamedQuery("SystemInstance.findBySystemInstance",
							SystemInstance.class)
					.setParameter("systemInstance", systemInstance)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error finding SystemInstance by systemInstance", ex);
			throw super.generateGHAEJBException(
					"systemInstance-findBySystemInstance-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#find(long)
	 */
	@Override
	public SystemInstance find(long Id) throws GHAEJBException {
		try {
			return em.find(SystemInstance.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding SystemInstance", e);
			throw super.generateGHAEJBException("systemInstance-find-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#getAll()
	 */
	@Override
	public List<SystemInstance> getAll() throws GHAEJBException {
		try {
			return em.createNamedQuery("SystemInstance.getAll",
					SystemInstance.class).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all SystemInstance", ex);
			throw super.generateGHAEJBException("systemInstance-getAll-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#save(org.fourgeeks
	 * .gha.domain.mix.SystemInstance)
	 */
	@Override
	public SystemInstance save(SystemInstance systemInstance)
			throws GHAEJBException {
		try {
			em.persist(systemInstance);
			em.flush();
			return em.find(SystemInstance.class, systemInstance.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving SystemInstance ", e);
			throw super.generateGHAEJBException("systemInstance-save-fail",
					RuntimeParameters.getLang(), em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#update(org.fourgeeks
	 * .gha.domain.mix.SystemInstance)
	 */
	@Override
	public SystemInstance update(SystemInstance systemInstance)
			throws GHAEJBException {
		try {
			SystemInstance res = em.merge(systemInstance);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update SystemInstance ", e);
			throw super.generateGHAEJBException("systemInstance-update-fail",
					RuntimeParameters.getLang(), em);
		}
	}
}