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
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#buildFilters(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public String buildFilters(EiaType eiaType) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#delete(long)
	 */
	@Override
	public void delete(long Id) throws EJBException {
		try{
			EiaTypeComponent entity = em.find(EiaTypeComponent.class, Id);
			em.remove(entity);
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: unable to delete eiatypecomponent", e);
			throw new EJBException("Error eliminando EiaTypeComponent por id "
					+ e.getCause().getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public List<EiaTypeComponent> find(EiaType eiaType) throws EJBException{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#find(org.fourgeeks.gha.domain.gmh.EiaType, int, int)
	 */
	@Override
	public List<EiaTypeComponent> find(EiaType eiaType, int offset, int size) throws EJBException{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#find(long)
	 */
	@Override
	public EiaTypeComponent find(long Id) throws EJBException {
		try {
			return em.find(EiaTypeComponent.class, Id);
		} catch (Exception e) {
			logger.log(Level.INFO, "Error buscando EiatypeComponent por Id ", e);
			throw new EJBException("Error buscando Eiatypecomponent por Id "
					+ e.getCause().getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#findByEiaTypeId(long)
	 */
	@Override
	public List<EiaTypeComponent> findByEiaTypeId(long Id) throws EJBException {
		String query = "SELECT e from EiaTypeComponent e " +
				"WHERE parentEiaTypeFk= :eiaTypeId";
		List <EiaTypeComponent> res = null;
		
		try{
			res = em.createQuery(query, EiaTypeComponent.class)
					.setParameter("eiaTypeId", Id)
					.getResultList();
		}catch(NoResultException e){
			logger.log(Level.INFO, "No results", e);
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retrieving all EiaTypeComponents", ex);
			throw new EJBException("Error obteniendo todos los eiaTypeComponents "
					+ ex.getCause().getMessage());
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#getAll()
	 */
	@Override
	public List<EiaTypeComponent> getAll() throws EJBException {
		String query = "SELECT e from EiaTypeComponent e order by parenteiatypefk";
		List <EiaTypeComponent> res = null;
		
		try{
			res = em.createQuery(query, EiaTypeComponent.class).getResultList();
		}catch(NoResultException e){
			logger.log(Level.INFO, "Get All: no results", e);
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retriving all EiaTypeComponents", ex);
			throw new EJBException("Error obteniendo todos los EiaTypeComponents"
					+ex.getCause().getMessage());
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#getAll(int, int)
	 */
	@Override
	public List<EiaTypeComponent> getAll(int offset, int size) throws EJBException {
		String query = "SELECT e from EiaTypeComponent e order by parenteiatypefk";
		List <EiaTypeComponent> res = null;
		
		try{
			res = em.createQuery(query, EiaTypeComponent.class)
					.setFirstResult(offset).setMaxResults(size).getResultList();
		}catch(NoResultException e){
			logger.log(Level.INFO, "Get All: no results", e);
		}catch(Exception ex){
			logger.log(Level.SEVERE, "Error retriving all EiaTypeComponents", ex);
			throw new EJBException("Error obteniendo todos los eiaTypeComponents "
					+ex.getCause().getMessage());
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#save(org.fourgeeks.gha.ejb.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent save(EiaTypeComponent eiaTypeComponent) throws EJBException {
		try{
			em.persist(eiaTypeComponent);
			em.flush();
			return em.find(EiaTypeComponent.class, eiaTypeComponent.getId());
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: saving eiaTypeComponent", e);
			throw new EJBException("Error guardando EiaTypeComponent: "
					+ e.getCause().getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote#update(org.fourgeeks.gha.ejb.gmh.EiaTypeComponent)
	 */
	@Override
	public EiaTypeComponent update(EiaTypeComponent eiaTypeComponent) throws EJBException {
		try{
			EiaTypeComponent res = em.merge(eiaTypeComponent);
			em.flush();
			return res;
		}catch(Exception e){
			logger.log(Level.INFO, "ERROR: unable to update eiatypecomponent", e);
			throw new EJBException("Error actualizando EiaTypeComponent " +e.getCause().getMessage());
		}

	}

}
