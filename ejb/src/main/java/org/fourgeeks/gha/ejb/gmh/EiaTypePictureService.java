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

import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Stateless(name = "gmh.EiaTypePictureService")
public class EiaTypePictureService implements EiaTypePictureServiceRemote {
	@PersistenceContext
	EntityManager em;

	private final static Logger logger = Logger
			.getLogger(EiaTypePictureService.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id)  throws GHAEJBException{
		try {
			EiaTypePicture entity = em.find(EiaTypePicture.class, Id);
			em.remove(entity);
			return true;

		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete EiaTypePicture", e);
			throw new GHAEJBException("Error eliminando EiaTypePicture por id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#find(org.fourgeeks
	 * .gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypePicture> find(EiaType eiaType)  throws GHAEJBException{
		try {
			return em
					.createNamedQuery("EiaTypePicture.findByEiaType",
							EiaTypePicture.class)
					.setParameter("eiaType", eiaType).getResultList();
		} catch (Exception ex) {
			logger.log(Level.SEVERE,
					"Error finding EiaTypePictures by eiaType", ex);
			throw new EJBException(
					"Error obteniendo EiaTypePictures por eiaType"
							+ ex.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#find(long)
	 */
	@Override
	public EiaTypePicture find(long Id) throws GHAEJBException {
		try {
			return em.find(EiaTypePicture.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando EiaTypePicture por Id ", e);
			throw new GHAEJBException("Error buscando EiaTypePicture por Id "
					+ e.getCause().getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#save(org.fourgeeks
	 * .gha.domain.gmh.EiaTypePicture)
	 */
	@Override
	public void save(EiaTypePicture eiaTypePicture) throws GHAEJBException {
			throws EJBException {
		try {
			em.persist(eiaTypePicture);
			em.flush();
			return em.find(EiaTypePicture.class, eiaTypePicture.getId());
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: saving EiaTypePicture", e);
			throw new GHAEJBException("Error guardando EiaTypePicture: "
					+ e.getCause().getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#update(org.fourgeeks
	 * .gha.domain.gmh.EiaTypePicture)
	 */
	@Override
	public boolean update(EiaTypePicture eiaTypePicture)  throws GHAEJBException{
		try {
			em.merge(eiaTypePicture);
			return true;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update eiaTypePicture", e);
			throw new GHAEJBException("Error actualizando eiaTypePicture " +e.getCause().getMessage());
					+ e.getCause().getMessage());
		}
	}

}
