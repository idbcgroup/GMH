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
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.EiaService")
public class EiaService implements EiaServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final Logger logger = Logger.getLogger(EiaService.class.getName());

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#save(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void save(Eia eia) {
		try{
			em.persist(eia);
		}catch(Exception e){
			logger.info("ERROR: saving object "+eia.toString());
			e.printStackTrace();
			
			//TODO: send exception to webclient
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(long)
	 */
	@Override
	public Eia find(long Id) {
		try{
			return em.find(Eia.class, Id);
		}catch(Exception e){
			e.printStackTrace();
			//TODO: send exception to webclient
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<Eia> find(Eia eia) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<Eia> find(EiaType eiaType) {
		List <Eia> res = null;
		String query = "SELECT e from Eia e JOIN e.eiaType t ";
		
		int varsAdded = 0;
		String filters = "";
		
		Brand brand = eiaType.getBrand();
		Manufacturer manufacturer = eiaType.getManufacturer();

		if (brand != null && brand.getId() > 0) {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			++varsAdded;
			filters += "t.brand='" + Long.toString(eiaType.getBrand().getId())
					+ "' ";
		}

		if (manufacturer != null && manufacturer.getId() > 0) {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			++varsAdded;
			filters += "t.manufacturer='"
					+ Long.toString(eiaType.getManufacturer().getId()) + "' ";
		}

		if (eiaType.getModel() != null) {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			++varsAdded;
			filters += " t.model like '%" + eiaType.getModel() + "%' ";
		}

		if (eiaType.getName() != null) {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			varsAdded++;
			filters += "t.name like '%" + eiaType.getName() + "%' ";
		}

		if (eiaType.getCode() != null) {
			if (varsAdded > 0) {
				filters += " OR ";
			}
			varsAdded++;
			filters += "t.code like '%" + eiaType.getCode() + "%' ";
		}
		
		if(varsAdded > 0)query += " WHERE " +filters;
		query += " order by e.id";
		
		try{
			res = em.createQuery(query, Eia.class).getResultList();
		}catch(Exception e){
			//TODO: Send exception to webclient
			e.printStackTrace();
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#getAll()
	 */
	@Override
	public List<Eia> getAll() {
		String query = "SELECT e from Eia e";
		List<Eia> res = null;
		try{
			res = em.createQuery(query, Eia.class).getResultList();
			logger.info("Get all Eias");
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
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#getAll(int, int)
	 */
	@Override
	public List<Eia> getAll(int offset, int size) {
		String query = "SELECT e from Eia e order by id";
		List<Eia> res = null;
		try{
			res = em.createQuery(query, Eia.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
			logger.info("Get all Eias");
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
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) {
		try {
			Eia entity = em.find(Eia.class, Id);
			em.remove(entity);

			logger.info("Deleted: " + entity.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("ERROR: unable to delete object="+Eia.class.getName() +" with id="
					+ Long.toString(Id));
			// TODO: send exception to webClient
		}
		
	}
}
