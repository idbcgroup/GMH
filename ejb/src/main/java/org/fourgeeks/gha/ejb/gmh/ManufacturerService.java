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
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.ManufacturerService")
public class ManufacturerService implements ManufacturerServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final static Logger logger = Logger.getLogger(Manufacturer.class.getName());
	
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try {
			Manufacturer entity = em.find(Manufacturer.class, Id);
			em.remove(entity);
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete manufacturer", e);
			throw new EJBException("Error eliminando Manufacturer por id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#find(long)
	 */
	@Override
	public Manufacturer find(long Id) throws EJBException {
		try{
			return em.find(Manufacturer.class, Id);
		}catch(Exception e){
			logger.log(Level.INFO, "Error buscando Manufacturer por Id ", e);
			throw new EJBException("Error buscando Manufacturer por Id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#find(org.fourgeeks.gha.domain.gmh.Manufacturer)
	 */
	@Override
	public List<Manufacturer> find(Manufacturer manufacturer) throws EJBException {
		List <Manufacturer> res = null;
		String query = "SELECT e from Manufacturer e where e.name like :manufacturerName";
		
		try{
			res = em.createQuery(query, Manufacturer.class)
					.setParameter("manufacturerName", manufacturer.getName())
					.getResultList();
		}catch(Exception ex){
			logger.log(Level.SEVERE,
					"Error obteniendo buscando los manufacturer por manufacturer", ex);
			throw new EJBException("Error obteniendo buscando los manufacturer por manufacturer "
					+ ex.getCause().getMessage());
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#getAll()
	 */
	@Override
	public List<Manufacturer> getAll() throws EJBException {
		String query = "SELECT e from Manufacturer e order by e.name";
		List<Manufacturer> res = null;
		try{
			res = em.createQuery(query, Manufacturer.class).getResultList();
		}catch(NoResultException ex){
			logger.log(Level.INFO, "No results", ex);
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all manufacturers", ex);
			throw new EJBException("Error obteniendo todos los manufacturers");
		}
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#save(org.fourgeeks.gha.domain.gmh.Manufacturer)
	 */
	@Override
	public Manufacturer save(Manufacturer manufacturer) throws EJBException {
		try{
			em.persist(manufacturer);
			em.flush();
			return em.find(Manufacturer.class, manufacturer.getId());
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: saving manufacturer", e);
			throw new EJBException("Error guardando manufacturer: "
					+ e.getCause().getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote#update(org.fourgeeks.gha.domain.gmh.Manufacturer)
	 */
	@Override
	public Manufacturer update(Manufacturer manufacturer) throws EJBException {
		try {
			Manufacturer res = em.merge(manufacturer);
			em.flush();
			return res;
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to update manufacturer", e);
			throw new EJBException("Error actualizando manufacturer " +e.getCause().getMessage());
		}
	}

}
