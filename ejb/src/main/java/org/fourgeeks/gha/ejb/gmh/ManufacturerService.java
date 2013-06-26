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

import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.ManufacturerService")
public class ManufacturerService implements ManufacturerServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final Logger logger = Logger.getLogger(Manufacturer.class.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#save(org.fourgeeks.gha.domain.gmh.Manufacturer)
	 */
	@Override
	public void save(Manufacturer manufacturer) {
		try{
			em.persist(manufacturer);
			em.flush();
		}catch(Exception e){
			logger.info("ERROR: saving object "+manufacturer.toString());
			e.printStackTrace();
			
			//TODO: send exception to webclient
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#find(long)
	 */
	@Override
	public Manufacturer find(long Id) {
		try{
			return em.find(Manufacturer.class, Id);
		}catch(Exception e){
			e.printStackTrace();
			//TODO: send exception to webclient
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) {
		try {
			Manufacturer entity = em.find(Manufacturer.class, Id);
			em.remove(entity);

			logger.info("Deleted: " + entity.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR: unable to delete object="+Manufacturer.class.getName() +" with id="
					+ Long.toString(Id));
			// TODO: send exception to webClient
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#getAll()
	 */
	@Override
	public List<Manufacturer> getAll() {
		String query = "SELECT e from Manufacturer e order by name";
		List<Manufacturer> res = null;
		try{
			res = em.createQuery(query, Manufacturer.class).getResultList();
			logger.info("Get all Manufacturer");
		}catch(NoResultException ex){
			logger.info("No results");
			//TODO: send exception to webclient
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all objects", ex);
			//TODO: Send exception to webclient
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#find(org.fourgeeks.gha.domain.gmh.Manufacturer)
	 */
	@Override
	public List<Manufacturer> find(Manufacturer manufacturer) {
		List <Manufacturer> res = null;
		String query = "SELECT e from Manufacturer e where name like '%"
				+manufacturer.getName()+"%' ";
		
		try{
			res = em.createQuery(query, Manufacturer.class).getResultList();
		}catch(Exception e){
			//TODO: Send exception to webclient
			e.printStackTrace();
		}
		
		return res;
	}

}
