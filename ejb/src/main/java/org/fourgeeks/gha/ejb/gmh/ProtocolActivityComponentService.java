/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.ProtocolActivity;
import org.fourgeeks.gha.domain.gmh.ProtocolActivityComponent;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.protocolActivityComponentService")
public class ProtocolActivityComponentService implements
		ProtocolActivityComponentServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ProtocolActivityComponentService.class.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityComponentServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			ProtocolActivityComponent entity = em.find(ProtocolActivityComponent.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete ProtocolActivityComponent",
					e);
			throw new EJBException("Error eliminando ProtocolActivityComponent por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityComponentServiceRemote#findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<ProtocolActivityComponent> findByProtocolActivity(
			ProtocolActivity protocolActivity) throws EJBException {
		try {
			return em
					.createNamedQuery(
							"ProtocolActivityComponent.findByProtocolActivity",
							ProtocolActivityComponent.class)
					.setParameter("parentProtocolActivity",
							protocolActivity).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding ProtocolActivity by ProtocolActivityComponent", e);
			throw new EJBException(
					"Error buscando ProtocolActivity por ProtocolActivityComponent"
							+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityComponentServiceRemote#find(long)
	 */
	@Override
	public ProtocolActivityComponent find(long Id) throws EJBException {
		try {
			return em.find(ProtocolActivityComponent.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding ProtocolActivityComponent", e);
			throw new EJBException("ERROR: finding ProtocolActivityComponent "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityComponentServiceRemote#getAll()
	 */
	@Override
	public List<ProtocolActivityComponent> getAll() throws EJBException {
		try {
			return em.createNamedQuery("ProtocolActivityComponent.getAll",
					ProtocolActivityComponent.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all ProtocolActivityComponent", e);
			throw new EJBException("Error buscando todos los ProtocolActivityComponent"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityComponentServiceRemote#getAll(int, int)
	 */
	@Override
	public List<ProtocolActivityComponent> getAll(int offset, int size)
			throws EJBException {
		try {
			return em
					.createNamedQuery("ProtocolActivityComponent.getAll",
							ProtocolActivityComponent.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all ProtocolActivityComponent", e);
			throw new EJBException("Error buscando todos los ProtocolActivityComponent"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityComponentServiceRemote#save(org.fourgeeks.gha.domain.gmh.ProtocolActivityComponent)
	 */
	@Override
	public ProtocolActivityComponent save(
			ProtocolActivityComponent protocolActivityComponent)
			throws EJBException {
		try {
			em.persist(protocolActivityComponent);
			em.flush();
			return em.find(ProtocolActivityComponent.class, protocolActivityComponent.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving ProtocolActivityComponent ", e);
			throw new EJBException("ERROR: saving ProtocolActivityComponent "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ProtocolActivityComponentServiceRemote#update(org.fourgeeks.gha.domain.gmh.ProtocolActivityComponent)
	 */
	@Override
	public ProtocolActivityComponent update(
			ProtocolActivityComponent protocolActivityComponent)
			throws EJBException {
		try {
			em.persist(protocolActivityComponent);
			em.flush();
			return em.find(ProtocolActivityComponent.class, protocolActivityComponent.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving ProtocolActivityComponent ", e);
			throw new EJBException("ERROR: saving ProtocolActivityComponent "
					+ e.getCause().getMessage());
		}
	}

}
