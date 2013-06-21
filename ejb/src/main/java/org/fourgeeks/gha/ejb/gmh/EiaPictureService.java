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

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPicture;

/**
 * @author emiliot
 *
 */

@Stateless(name="gmh.EiaPictureService")
public class EiaPictureService implements EiaPictureServiceRemote{
	@PersistenceContext
	EntityManager em;
	
	private final Logger logger = Logger.getLogger(EiaTypePictureService.class.getName());
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#save(org.fourgeeks.gha.domain.gmh.EiaPicture)
	 */
	@Override
	public void save(EiaPicture eiaPicture) {
		try{
			em.persist(eiaPicture);
		}catch(Exception e){
			logger.info("ERROR: saving object "+eiaPicture.toString());
			e.printStackTrace();
			
			//TODO: send exception to webclient
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id) {
		try {
			EiaPicture entity = em.find(EiaPicture.class, Id);
			em.remove(entity);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR: unable to delete object="+EiaPicture.class.getName() +" with id="
					+ Long.toString(Id));
			// TODO: send exception to webClient
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#update(org.fourgeeks.gha.domain.gmh.EiaPicture)
	 */
	@Override
	public boolean update(EiaPicture eiaPicture) {
		try{
			em.merge(eiaPicture);
			return true;
		}catch(Exception e){
			logger.info("ERROR: unable to update object " + 
					eiaPicture.toString());
			e.printStackTrace();
			// TODO: send exception to webClient
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#find(long)
	 */
	@Override
	public EiaPicture find(long Id) {
		try{
			return em.find(EiaPicture.class, Id);
		}catch(Exception e){
			e.printStackTrace();
			//TODO: send exception to webclient
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaPictureServiceRemote#find(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<EiaPicture> find(Eia eia) {
		List <EiaPicture> res = null;
		String query = "SELECT e from EiaPicture e WHERE eiaFk=:eiaId order by id";
		
		try{
			res = em.createQuery(query, EiaPicture.class)
					.setParameter("eiaId", eia.getId())
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
