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
	
	private final Logger logger = Logger.getLogger(EiaTypePictureService.class.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#save(org.fourgeeks.gha.domain.gmh.EiaTypePicture)
	 */
	@Override
	public void save(EiaTypePicture eiaTypePicture) {
		try{
			em.persist(eiaTypePicture);
		}catch(Exception e){
			logger.info("ERROR: saving object "+eiaTypePicture.toString());
			e.printStackTrace();
			
			//TODO: send exception to webclient
		}
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id) {
		try {
			EiaTypePicture entity = em.find(EiaTypePicture.class, Id);
			em.remove(entity);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR: unable to delete object="+EiaTypePicture.class.getName() +" with id="
					+ Long.toString(Id));
			// TODO: send exception to webClient
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#update(org.fourgeeks.gha.domain.gmh.EiaTypePicture)
	 */
	@Override
	public boolean update(EiaTypePicture eiaTypePicture) {
		try{
			em.merge(eiaTypePicture);
			return true;
		}catch(Exception e){
			logger.info("ERROR: unable to update object " + 
					eiaTypePicture.toString());
			e.printStackTrace();
			// TODO: send exception to webClient
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#find(long)
	 */
	@Override
	public EiaTypePicture find(long Id) {
		try{
			return em.find(EiaTypePicture.class, Id);
		}catch(Exception e){
			e.printStackTrace();
			//TODO: send exception to webclient
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypePictureServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypePicture> find(EiaType eiaType) {
		List <EiaTypePicture> res = null;
		String query = "SELECT e from EiaTypePicture e WHERE eiaTypeFk=:eiaTypeId order by id";
		
		try{
			res = em.createQuery(query, EiaTypePicture.class)
					.setParameter("eiaTypeId", eiaType.getId())
					.getResultList();
		}catch(NoResultException ex){
			logger.info("No results");
			//TODO: send exception to webclient
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all objects", ex);
			//TODO: Send exception to webclient
		}
		
		return res;
	}

}
