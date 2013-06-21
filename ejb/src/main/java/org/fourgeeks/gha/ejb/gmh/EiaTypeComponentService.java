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
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.EiaTypeComponentService")
public class EiaTypeComponentService implements EiaTypeComponentServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final Logger logger = Logger.getLogger(EiaTypeComponentService.class
			.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#save(org.fourgeeks.gha.ejb.gmh.EiaTypeComponent)
	 */
	@Override
	public void save(EiaTypeComponent eiaTypeComponent) {
		try{
			em.persist(eiaTypeComponent);
		}catch(Exception e){
			logger.info("ERROR: saving object " + eiaTypeComponent.toString());
			e.printStackTrace();
			// TODO: send exception to webClient
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#find(long)
	 */
	@Override
	public EiaTypeComponent find(long Id) {
		try {
			return em.find(EiaTypeComponent.class, Id);
		} catch (Exception e) {
			// TODO: send exception to webClient
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeComponent> find(EiaType eiaType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<EiaTypeComponent> find(EiaType eiaType, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) {
		try{
			EiaTypeComponent entity = em.find(EiaTypeComponent.class, Id);
			em.remove(entity);
		}catch(Exception e){
			logger.info("ERROR: unable to delete object="+EiaTypeComponent.class.getName()+
					" with id="+ Long.toString(Id));
			e.printStackTrace();
			//TODO: send exception to webclient
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#update(org.fourgeeks.gha.ejb.gmh.EiaTypeComponent)
	 */
	@Override
	public void update(EiaTypeComponent eiaTypeComponent) {
		try{
			em.merge(eiaTypeComponent);
		}catch(Exception e){
			logger.info("ERROR: unable to update object " + eiaTypeComponent.toString());
			e.printStackTrace();
			// TODO: send exception to webClient
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#getAll()
	 */
	@Override
	public List<EiaTypeComponent> getAll() {
		String query = "SELECT e from EiaTypeComponent e order by parenteiatypefk";
		List <EiaTypeComponent> res = null;
		
		try{
			res = em.createQuery(query, EiaTypeComponent.class).getResultList();
			logger.info("Get all EiaTypeComponents");
		}catch(NoResultException e){
			logger.info("No Results");
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeComponents", ex);
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#getAll(int, int)
	 */
	@Override
	public List<EiaTypeComponent> getAll(int offset, int size) {
		String query = "SELECT e from EiaTypeComponent e order by parenteiatypefk";
		List <EiaTypeComponent> res = null;
		
		try{
			res = em.createQuery(query, EiaTypeComponent.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
			logger.info("Get all EiaTypeComponents");
		}catch(NoResultException e){
			logger.info("No Results");
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeComponents", ex);
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#buildFilters(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public String buildFilters(EiaType eiaType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#findByEiaTypeId(long)
	 */
	@Override
	public List<EiaTypeComponent> findByEiaTypeId(long Id) {
		String query = "SELECT e from EiaTypeComponent e " +
				"WHERE parentEiaTypeFk= :eiaTypeId";
		List <EiaTypeComponent> res = null;
		
		try{
			res = em.createQuery(query, EiaTypeComponent.class)
					.setParameter("eiaTypeId", Id)
					.getResultList();
		}catch(NoResultException e){
			logger.info("No results");
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeComponents", ex);
		}
		
		return res;
	}

}
