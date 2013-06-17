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

import org.fourgeeks.gha.domain.gmh.Brand;

/**
 * @author emiliot
 *
 */
@Stateless(name = "gmh.BrandService")
public class BrandService implements BrandServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final Logger logger = Logger.getLogger(BrandService.class.getName());

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#save(org.fourgeeks.gha.domain.gmh.Brand)
	 */
	@Override
	public void save(Brand brand) {
		try{
			em.persist(brand);
		}catch(Exception e){
			logger.info("ERROR: saving object "+brand.toString());
			e.printStackTrace();
			
			//TODO: send exception to webclient
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#find(long)
	 */
	@Override
	public Brand find(long Id) {
		try{
			return em.find(Brand.class, Id);
		}catch(Exception e){
			e.printStackTrace();
			//TODO: send exception to webclient
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) {
		try {
			Brand entity = em.find(Brand.class, Id);
			em.remove(entity);

			logger.info("Deleted: " + entity.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR: unable to delete object="+Brand.class.getName() +" with id="
					+ Long.toString(Id));
			// TODO: send exception to webClient
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#getAll()
	 */
	@Override
	public List<Brand> getAll() {
		String query = "SELECT e from Brand e order by name";
		List<Brand> res = null;
		try{
			res = em.createQuery(query, Brand.class).getResultList();
			logger.info("Get all Brands");
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
	 * @see org.fourgeeks.gha.ejb.gmh.BrandServiceRemote#find(org.fourgeeks.gha.domain.gmh.Brand)
	 */
	@Override
	public List<Brand> find(Brand brand) {
		List <Brand> res = null;
		String query = "SELECT e from Brand e where name like '%"
				+brand.getName()+"%' ";
		
		try{
			res = em.createQuery(query, Brand.class).getResultList();
		}catch(Exception e){
			//TODO: Send exception to webclient
			e.printStackTrace();
		}
		
		return res;
	}

}
