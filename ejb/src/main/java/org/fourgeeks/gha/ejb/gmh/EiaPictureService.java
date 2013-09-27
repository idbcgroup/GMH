/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPicture;

/**
 * @author emiliot
 * 
 */

@Stateless(name = "gmh.EiaPictureService")
public class EiaPictureService implements EiaPictureServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaPictureService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id) throws GHAEJBException {
		try {
			EiaPicture entity = em.find(EiaPicture.class, Id);
			em.remove(entity);
			return true;

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete EiaPicture", e);
			throw new GHAEJBException("Error eliminando EiaPicture por id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#find(org.fourgeeks.
	 * gha.domain.gmh.Eia)
	 */
	@Override
	public List<EiaPicture> find(Eia eia) throws GHAEJBException {
		List<EiaPicture> res = null;
		try {
			res = em.createNamedQuery("EiaPicture.findByEia", EiaPicture.class)
					.setParameter("eia", eia).getResultList();

		} catch (NoResultException e) {
			logger.log(Level.INFO, "No results", e);
		} catch (Exception ex) {
			logger.log(Level.INFO, "Error buscando EiaPicture por Eia ", ex);
			throw new GHAEJBException("Error buscando EiaPicture por Eia "
					+ ex.getCause().getMessage());
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#find(long)
	 */
	@Override
	public EiaPicture find(long Id) throws GHAEJBException {
		try {
			return em.find(EiaPicture.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando EiaPicture por Id ", e);
			throw new GHAEJBException("Error buscando EiaPicture por Id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#save(org.fourgeeks.
	 * gha.domain.gmh.EiaPicture)
	 */
	@Override
	public EiaPicture save(EiaPicture eiaPicture) throws GHAEJBException {
		try {
			em.persist(eiaPicture);
			em.flush();
			return em.find(EiaPicture.class, eiaPicture.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaPicture", e);
			throw new GHAEJBException("Error guardando EiaPicture: "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#update(org.fourgeeks
	 * .gha.domain.gmh.EiaPicture)
	 */
	@Override
	public boolean update(EiaPicture eiaPicture) throws GHAEJBException {
		try {
			em.merge(eiaPicture);
			return true;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiaPicture", e);
			throw new GHAEJBException("Error actualizando eiaPicture "
					+ e.getCause().getMessage());
		}
	}

}
