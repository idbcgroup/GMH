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
import org.fourgeeks.gha.domain.exceptions.EJBException;

/**
 * @author emiliot
 *
 */

@Stateless(name = "ess.InstanceLogonService")
public class InstanceLogonService implements InstanceLogonServiceRemote {
	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(InstanceLogonService.class
			.getName());
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			InstanceLogon entity = em.find(InstanceLogon.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete InstanceLogon", e);
			throw new EJBException("ERROR: unable to delete InstanceLogon "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#find(org.fourgeeks.gha.domain.ess.InstanceLogon)
	 */
	@Override
	public List<InstanceLogon> find(InstanceLogon instanceLogon)
			throws EJBException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#find(long)
	 */
	@Override
	public InstanceLogon find(long Id) throws EJBException {
		try {
			return em.find(InstanceLogon.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding InstanceLogon", e);
			throw new EJBException("ERROR: finding InstanceLogon "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#getAll()
	 */
	@Override
	public List<InstanceLogon> getAll() throws EJBException {
		try {
			return em.createNamedQuery("InstanceLogon.getAll", InstanceLogon.class)
					.getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE, "Error retrieving all InstanceLogon", ex);
			throw new EJBException("Error obteniendo todas las InstanceLogon"
					+ ex.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#save(org.fourgeeks.gha.domain.ess.InstanceLogon)
	 */
	@Override
	public InstanceLogon save(InstanceLogon instanceLogon) throws EJBException {
		try {
			em.persist(instanceLogon);
			em.flush();
			return em.find(InstanceLogon.class, instanceLogon.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving InstanceLogon ", e);
			throw new EJBException("ERROR: saving InstanceLogon "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.ess.InstanceLogonServiceRemote#update(org.fourgeeks.gha.domain.ess.InstanceLogon)
	 */
	@Override
	public InstanceLogon update(InstanceLogon instanceLogon)
			throws EJBException {
		try {
			InstanceLogon res = em.merge(instanceLogon);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO,
					"ERROR: unable to update InstanceLogon ", e);
			throw new EJBException("ERROR: no se puede actualizar el InstanceLogon "
					+ e.getCause().getMessage());
		}
	}

}