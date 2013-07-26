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

import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;

/**
 * @author emiliot
 *
 */

@Stateless(name = "gmh.EiaService")
public class EiaService implements EiaServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	private final static Logger logger = Logger.getLogger(EiaService.class.getName());

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#buildFilters(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public String buildFilters(EiaType eiaType) {
		Brand brand = eiaType.getBrand();
		Manufacturer manufacturer = eiaType.getManufacturer();
		
		String filters = "";
		int varsAdded = 0;
		
		if (brand != null && brand.getId() > 0) {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			++varsAdded;
			filters += "t.brand='" + Long.toString(eiaType.getBrand().getId())
					+ "' ";
		}

		if (manufacturer != null && manufacturer.getId() > 0) {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			++varsAdded;
			filters += "t.manufacturer='"
					+ Long.toString(eiaType.getManufacturer().getId()) + "' ";
		}

		if (eiaType.getModel() != null && eiaType.getModel() != "") {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			++varsAdded;
			filters += " lower(t.model) like '%" + eiaType.getModel().toLowerCase() + "%' ";
		}

		if (eiaType.getName() != null && eiaType.getName() != "") {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			varsAdded++;
			filters += "lower(t.name) like '%" + eiaType.getName().toLowerCase() + "%' ";
		}

		if (eiaType.getCode() != null && eiaType.getCode() != "") {
			if (varsAdded > 0) {
				filters += " AND ";
			}
			varsAdded++;
			filters += "lower(t.code) like '%" + eiaType.getCode().toLowerCase() + "%' ";
		}
		
		return filters;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#delete(long)
	 */
	@Override
	public boolean delete(long Id) throws EJBException {
		try {
			Eia entity = em.find(Eia.class, Id);
			em.remove(entity);
			return true;
			
		} catch (Exception e) {
			logger.log(Level.INFO, "ERROR: unable to delete eia="+Eia.class.getName() +" with id="
					+ Long.toString(Id), e);
			throw new EJBException("ERROR: eliminando un eia por id "+
					e.getCause().getMessage());
		}
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public List<Eia> find(Eia eia) throws EJBException{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<Eia> find(EiaType eiaType) throws EJBException{
		List <Eia> res = null;
		String query = "SELECT e from Eia e JOIN e.eiaType t ";
		String filters = buildFilters(eiaType);
		
		if(filters != "")query += " WHERE " +filters;
		query += " order by e.id";
		
		try{
			res = em.createQuery(query, Eia.class).getResultList();
			return res;
		}catch(Exception e){
			logger.log(Level.INFO, "Error: finding eia by eiatype", e);
			throw new EJBException("Error buscando eia por eiatype "+
					e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#find(long)
	 */
	@Override
	public Eia find(long Id) throws EJBException{
		try{
			return em.find(Eia.class, Id);
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: finding eia by id", e);
			throw new EJBException("Error buscando eia por id "+e.getCause().getMessage()); 
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#getAll()
	 */
	@Override
	public List<Eia> getAll() throws EJBException {
		try{
			return em.createNamedQuery("Eia.getAll", Eia.class).getResultList();
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all eias", ex);
			throw new EJBException("Error obteniendo todos los eias" +
					ex.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#getAll(int, int)
	 */
	@Override
	public List<Eia> getAll(int offset, int size) throws EJBException {
		try{
			return em.createNamedQuery("Eia.getAll", Eia.class).setFirstResult(offset)
					.setMaxResults(size).getResultList();
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all eias", ex);
			throw new EJBException("Error obteniendo todos los eias" +
					ex.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#save(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public Eia save(Eia eia) throws EJBException {
		try{
			em.persist(eia);
			em.flush();
			return em.find(Eia.class, eia.getId());
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: saving eia "+eia.toString(), e);
			throw new EJBException("ERROR: guardando eia "+eia.toString()
					+ " " +e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaServiceRemote#update(org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public Eia update(Eia eia) throws EJBException {
		try{
			Eia res = em.merge(eia);
			em.flush();
			return res;
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: unable to update eia " 
					+ eia.toString(), e);
			throw new EJBException("ERROR: no se puede eliminar el eia "+
					e.getCause().getMessage());
		}
		
	}
}
