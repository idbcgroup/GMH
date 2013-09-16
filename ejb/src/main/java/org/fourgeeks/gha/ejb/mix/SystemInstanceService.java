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

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.mix.SystemInstance;

/**
 * @author emiliot
 *
 */
@Stateless(name = "mix.SystemInstanceService")
public class SystemInstanceService implements SystemInstanceRemoteService {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(SystemInstanceService.class
			.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			SystemInstance entity = em.find(SystemInstance.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete SystemInstance", e);
			throw new EJBException("ERROR: unable to delete SystemInstance "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#find(org.fourgeeks.gha.domain.mix.SystemInstance)
	 */
	@Override
	public List<SystemInstance> find(SystemInstance systemInstance)
			throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#find(long)
	 */
	@Override
	public SystemInstance find(long Id) throws EJBException {
		try {
			return em.find(SystemInstance.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding SystemInstance", e);
			throw new EJBException("ERROR: finding SystemInstance "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#getAll()
	 */
	@Override
	public List<SystemInstance> getAll() throws EJBException {
		try {
			return em.createNamedQuery("SystemInstance.getAll", SystemInstance.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all SystemInstance", ex);
			throw new EJBException("Error obteniendo todas las SystemInstance"
					+ ex.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#save(org.fourgeeks.gha.domain.mix.SystemInstance)
	 */
	@Override
	public SystemInstance save(SystemInstance systemInstance)
			throws EJBException {
		try {
			em.persist(systemInstance);
			em.flush();
			return em.find(SystemInstance.class, systemInstance.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving SystemInstance ", e);
			throw new EJBException("ERROR: saving SystemInstance "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.domain.mix.SystemInstanceRemoteService#update(org.fourgeeks.gha.domain.mix.SystemInstance)
	 */
	@Override
	public SystemInstance update(SystemInstance systemInstance)
			throws EJBException {
		try {
			SystemInstance res = em.merge(systemInstance);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update SystemInstance ", e);
			throw new EJBException("ERROR: no se puede actualizar el SystemInstance "
					+ e.getCause().getMessage());
		}
	}

}
