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
import org.fourgeeks.gha.domain.gmh.RaS;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.ResourceService")
public class ResourceService implements RaSServiceRemote {
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
			RaS entity = em.find(RaS.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete Resource/Service",
					e);
			throw new EJBException("Error eliminando Resource/Service por id "
					+ e.getCause().getMessage());
		}
	}

//	public List<RaS> findByProtocolActivity(
//			ProtocolActivity protocolActivity) throws EJBException {
//		try {
//			return em.createNamedQuery("Resource.findByProtocolActivity",
//							RaS.class)
//					.setParameter("protocolActivity", protocolActivity).getResultList();
//		} catch (Exception e) {
//			logger.log(Level.INFO, "Error: finding Resources by ProtocolActivity", e);
//			throw new EJBException(
//					"Error buscando Resource por ProtocolActivity"
//							+ e.getCause().getMessage());
//		}
//	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#find(long)
	 */
	@Override
	public RaS find(long Id) throws EJBException {
		try {
			return em.find(RaS.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: finding Resource/Service", e);
			throw new EJBException("ERROR: finding Resource/Service "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.RaSServiceRemote#findByProtocolActivity(org.fourgeeks.gha.domain.gmh.ProtocolActivity)
	 */
	@Override
	public List<RaS> findByProtocolActivity(ProtocolActivity protocolActivity)
			throws EJBException {
		try {
			return em.createNamedQuery("RaS.findByProtocolActivity",
							RaS.class)
					.setParameter("protocolActivity", protocolActivity).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding Resources/Services by ProtocolActivity", e);
			throw new EJBException(
					"Error buscando Resources/Services por ProtocolActivity"
							+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#getAll()
	 */
	@Override
	public List<RaS> getAll() throws EJBException {
		try {
			return em.createNamedQuery("RaS.getAll",
					RaS.class).getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource/Service", e);
			throw new EJBException("Error buscando todos los Resource/Service"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#getAll(int, int)
	 */
	@Override
	public List<RaS> getAll(int offset, int size) throws EJBException {
		try {
			return em.createNamedQuery("RaS.getAll",
					RaS.class).setFirstResult(offset).setMaxResults(size)
					.getResultList();
		} catch (Exception e) {
			logger.log(Level.INFO, "Error: finding all Resource/Service", e);
			throw new EJBException("Error buscando todos los Resource/Service"
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#save(org.fourgeeks.gha.domain.gmh.RaS)
	 */
	@Override
	public RaS save(RaS ras) throws EJBException {
		try {
			em.persist(ras);
			em.flush();
			return em.find(RaS.class, ras.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving Resource/Service ", e);
			throw new EJBException("ERROR: saving Resource/Service "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ResourceServiceRemote#update(org.fourgeeks.gha.domain.gmh.RaS)
	 */
	@Override
	public RaS update(RaS ras) throws EJBException {
		try {
			RaS res = em.merge(ras);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update Resource/Service ",
					e);
			throw new EJBException(
					"ERROR: no se puede actualizar el Resource/Service "
							+ e.getCause().getMessage());
		}
	}

}
