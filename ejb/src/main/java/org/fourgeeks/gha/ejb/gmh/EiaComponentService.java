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
import org.fourgeeks.gha.domain.gmh.EiaComponent;

/**
 * @author emiliot
 *
 */
@Stateless(name = "gmh.EiaComponentService")
public class EiaComponentService implements EiaComponentServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final Logger logger = Logger.getLogger(EiaComponentService.class.getName());
	
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#save(org.fourgeeks.gha.ejb.gmh.EiaComponent)
	 */
	@Override
	public void save(EiaComponent eiaComponent) {
		try{
			em.persist(eiaComponent);
		}catch(Exception e){
			logger.info("ERROR: saving object " + eiaComponent.toString());
			e.printStackTrace();
			//TODO: send exception to webclient
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#find(long)
	 */
	@Override
	public EiaComponent find(long Id) {
		try{
			return em.find(EiaComponent.class, Id);
		}catch(Exception e){
			//TODO: send exception to webclient
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#find(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<EiaComponent> find(Eia eia) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#find(org.fourgeeks.gha.domain.gmh.Eia, int, int)
	 */
	@Override
	public List<EiaComponent> find(Eia eia, int offset, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) {
		try{
			EiaComponent entity = em.find(EiaComponent.class, Id);
			em.remove(entity);
			logger.info("Deleted: " + entity.toString());
		}catch(Exception e){
			logger.info("ERROR: unable to delete object="+
					EiaComponent.class.getName()+" with id="
					+ Long.toString(Id));
			
			e.printStackTrace();
			
			//TODO: Send exception to WebClient
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#update(org.fourgeeks.gha.ejb.gmh.EiaComponent)
	 */
	@Override
	public void update(EiaComponent eiaComponent) {
		try{
			em.merge(eiaComponent);
		}catch(Exception e){
			logger.info("ERROR: unable to update object " + eiaComponent.toString());
			e.printStackTrace();
			//TODO: send exception to Webclient
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#getAll()
	 */
	@Override
	public List<EiaComponent> getAll() {
		String query = "SELECT e from EiaComponent e order by eia";
		List<EiaComponent> res = null;
		try{
			res = em.createQuery(query, EiaComponent.class).getResultList();
			logger.info("Get all eiaComponents");
		}catch(NoResultException e){
			logger.info("No Results");
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all eiaComponents", ex);
			//TODO: send exception to webclient
		}
		
		return res;
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#getAll(int, int)
	 */
	@Override
	public List<EiaComponent> getAll(int offset, int size) {
		String query = "SELECT e from EiaComponent e order by eiaparentfk";
		List<EiaComponent> res = null;
		try{
			res = em.createQuery(query, EiaComponent.class)
					.setFirstResult(offset).setMaxResults(size)
					.getResultList();
			logger.info("Get all eiaComponents");
		}catch(NoResultException e){
			logger.info("No Results");
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all eiaComponents", ex);
			ex.printStackTrace();
			//TODO: send exception to webclient
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote#findByEiaId(long)
	 */
	@Override
	public List<EiaComponent> findByEiaId(long Id) {
		List <EiaComponent> res = null;
		String query = "SELECT e from EiaComponent e " +
				"WHERE parentEiaFk = :eiaId";
		try{
			res = em.createQuery(query, EiaComponent.class)
					.setParameter("eiaId", Id)
					.getResultList();
		}catch(NoResultException e){
			logger.info("No results");
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all eiaComponents", ex);
			ex.printStackTrace();
			//TODO: SEND exception to webclient
		}
		
		return res;
	}

}
