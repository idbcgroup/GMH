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

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypePicture;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.EiaTypePictureService")
public class EiaTypePictureService implements EiaTypePictureServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final static Logger logger = Logger.getLogger(EiaTypePictureService.class.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id)  throws EJBException{
		try {
			EiaTypePicture entity = em.find(EiaTypePicture.class, Id);
			em.remove(entity);
			return true;
			
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete EiaTypePicture", e);
			throw new EJBException("Error eliminando EiaTypePicture por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypePicture> find(EiaType eiaType)  throws EJBException{
		List <EiaTypePicture> res = null;
		String query = "SELECT e from EiaTypePicture e WHERE eiaTypeFk=:eiaTypeId order by id";
		
		try{
			res = em.createQuery(query, EiaTypePicture.class)
					.setParameter("eiaTypeId", eiaType.getId())
					.getResultList();
		}catch(NoResultException ex){
			logger.log(Level.INFO, "No results", ex);
		}catch(Exception ex){
			logger.log(Level.INFO, "Error buscando EiaTypePicture por EiaType ", ex);
			throw new EJBException("Error buscando EiaTypePicture por EiaType "
					+ ex.getCause().getMessage());
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#find(long)
	 */
	@Override
	public EiaTypePicture find(long Id) throws EJBException {
		try{
			return em.find(EiaTypePicture.class, Id);
		}catch(Exception e){
			logger.log(Level.INFO, "Error buscando EiaTypePicture por Id ", e);
			throw new EJBException("Error buscando EiaTypePicture por Id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#save(org.fourgeeks.gha.domain.gmh.EiaTypePicture)
	 */
	@Override
	public void save(EiaTypePicture eiaTypePicture) throws EJBException {
		try{
			em.persist(eiaTypePicture);
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: saving EiaTypePicture", e);
			throw new EJBException("Error guardando EiaTypePicture: "
					+ e.getCause().getMessage());
		}
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#update(org.fourgeeks.gha.domain.gmh.EiaTypePicture)
	 */
	@Override
	public boolean update(EiaTypePicture eiaTypePicture)  throws EJBException{
		try{
			em.merge(eiaTypePicture);
			return true;
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: unable to update eiaTypePicture", e);
			throw new EJBException("Error actualizando eiaTypePicture " +e.getCause().getMessage());
		}
	}

}