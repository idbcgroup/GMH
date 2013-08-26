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
import org.fourgeeks.gha.domain.gmh.Resource;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.ResourceService")
public class ResourceService implements ResourceServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(ResourceService.class.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			Resource entity = em.find(Resource.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Resource",
					e);
			throw new EJBException("Error eliminando Resource por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<Resource> findByProtocolActivity(
			ProtocolActivity protocolActivity) throws EJBException {
		try {
			return em.createNamedQuery("Resource.findByProtocolActivity",
							Resource.class)
					.setParameter("protocolActivity", protocolActivity).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Resources by ProtocolActivity", e);
			throw new EJBException(
					"Error buscando Resource por ProtocolActivity"
							+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#find(long)
	 */
	@Override
	public Resource find(long Id) throws EJBException {
		try {
			return em.find(Resource.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Resource", e);
			throw new EJBException("ERROR: finding Resource "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#getAll()
	 */
	@Override
	public List<Resource> getAll() throws EJBException {
		try {
			return em.createNamedQuery("Resource.getAll",
					Resource.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource", e);
			throw new EJBException("Error buscando todos los Resource"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#getAll(int, int)
	 */
	@Override
	public List<Resource> getAll(int offset, int size) throws EJBException {
		try {
			return em.createNamedQuery("Resource.getAll",
					Resource.class).setFirstResult(offset).setMaxResults(size)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource", e);
			throw new EJBException("Error buscando todos los Resource"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#save(org.fourgeeks.gha.domain.gmh.Resource)
	 */
	@Override
	public Resource save(Resource resource) throws EJBException {
		try {
			em.persist(resource);
			em.flush();
			return em.find(Resource.class, resource.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Resource ", e);
			throw new EJBException("ERROR: saving Resource "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#update(org.fourgeeks.gha.domain.gmh.Resource)
	 */
	@Override
	public Resource update(Resource resource) throws EJBException {
		try {
			Resource res = em.merge(resource);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Resource ",
					e);
			throw new EJBException(
					"ERROR: no se puede actualizar el Resource "
							+ e.getCause().getMessage());
		}
	}

}
